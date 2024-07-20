package com.api.takeiteasystock.application.service

import com.api.takeiteasystock.application.dto.reqeust.UpdateRequestDto
import com.api.takeiteasystock.core.util.JpaService
import com.api.takeiteasystock.domain.entity.Stock
import com.api.takeiteasystock.domain.repository.StockRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class StockService(
    private val repository: StockRepository,
    private val objectMapper: ObjectMapper
):JpaService<Stock, UUID> {


    override var jpaRepository: JpaRepository<Stock, UUID> = repository

    @Transactional
    @KafkaListener(topics = ["order"], groupId = "stock-group")
    fun listen(record: ConsumerRecord<String, String>) {
        when (record.key()){
            "ORDER_COMPLETED" -> {
                val requestDto = objectMapper.readValue(record.value(), UpdateRequestDto::class.java)
                decrease(requestDto)
            }
            "ORDER_CANCEL" -> {
                val requestDto = objectMapper.readValue(record.value(), UpdateRequestDto::class.java)
                increase(requestDto)
            }
        }
    }

    private fun decrease(requestDto: UpdateRequestDto) {
        requestDto.product.map { item ->
            val stock = repository.findByProductId(UUID.fromString(item.productId))
            stock.quantity.minus(item.quantity)
            saveModel(stock)
        }
    }
    // 롤백, 주문 취소에 사용되는 메소드
    fun increase(requestDto: UpdateRequestDto) {
        requestDto.product.map { item ->
            val stock = repository.findByProductId(UUID.fromString(item.productId))
            stock.quantity.plus(item.quantity)
            saveModel(stock)
        }
    }



}