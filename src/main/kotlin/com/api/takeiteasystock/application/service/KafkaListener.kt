package com.api.takeiteasystock.application.service

import com.api.takeiteasystock.application.dto.reqeust.RegisterProductRequestDto
import com.api.takeiteasystock.application.dto.reqeust.UpdateProductRequestDto
import com.api.takeiteasystock.application.dto.reqeust.UpdateRequestDto
import com.api.takeiteasystock.domain.entity.KafkaOperationType.*
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class KafkaListener(
    val stockService: StockService,
    private val objectMapper: ObjectMapper
) {

    @Transactional
    @KafkaListener(topics = ["product"], groupId = "stock-group")
    fun updateProductListener(record: ConsumerRecord<String, String>) {
        when (record.key()){
            PRODUCT_CREATE.name -> {
                val requestDto = objectMapper.readValue(record.value(), RegisterProductRequestDto::class.java)
                stockService.register(requestDto)
            }
            PRODUCT_UPDATE.name -> {
                val requestDto = objectMapper.readValue(record.value(), UpdateProductRequestDto::class.java)
                stockService.update(requestDto)
            }
        }
    }

    @Transactional
    @KafkaListener(topics = ["order"], groupId = "stock-group")
    fun orderListener(record: ConsumerRecord<String, String>) {
        when (record.key()){
            ORDER_COMPLETED.name -> {
                val requestDto = objectMapper.readValue(record.value(), UpdateRequestDto::class.java)
                stockService.decrease(requestDto)
            }
            ORDER_CANCELLED.name -> {
                val requestDto = objectMapper.readValue(record.value(), UpdateRequestDto::class.java)
                stockService.increase(requestDto)
            }
        }
    }

}