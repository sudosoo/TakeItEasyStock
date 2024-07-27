package com.api.takeiteasystock.application.service

import com.api.takeiteasystock.application.dto.reqeust.OrderRequestDto
import com.api.takeiteasystock.application.dto.reqeust.RegisterProductRequestDto
import com.api.takeiteasystock.application.dto.reqeust.UpdateProductRequestDto
import com.api.takeiteasystock.application.dto.reqeust.UpdateRequestDto
import com.api.takeiteasystock.domain.entity.KafkaOperationType.*
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.extern.slf4j.Slf4j
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.helpers.Reporter.info
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Slf4j
class KafkaListenerService(
    val stockService: StockService,
    val objectMapper: ObjectMapper
) {
    //TODO 이벤트 id를 받아서 요청을 멱등성 있게 처리 해야 함

    @Transactional
    @KafkaListener(topics = ["product"], groupId = "stock-group")
    fun updateProductListener(record: ConsumerRecord<String, String>) {
        when (record.key()){
            PRODUCT_CREATE.name -> {
                val requestDto = objectMapper.readValue(record.value(), RegisterProductRequestDto::class.java)
                stockService.register(requestDto)
                info("Successfully registered product: $requestDto")
            }
            PRODUCT_UPDATE.name -> {
                val requestDto = objectMapper.readValue(record.value(), UpdateProductRequestDto::class.java)
                stockService.update(requestDto)
                info("Successfully updated product: $requestDto")
            }
        }
    }

    @Transactional
    @KafkaListener(topics = ["ORDER"], groupId = "stock-group")
    fun orderListener(record: ConsumerRecord<String, String>) {
        when (record.key()){
            ORDER_COMPLETED.name -> {
                val requestDto = objectMapper.readValue(record.value(), UpdateRequestDto::class.java)
                stockService.decrease(requestDto)
                info("Successfully decreased stock: $requestDto")
            }
            ORDER_CANCELLED.name -> {
                val requestDto = objectMapper.readValue(record.value(), UpdateRequestDto::class.java)
                stockService.increase(requestDto)
                info("Successfully increased stock: $requestDto")
            }
        }
    }

}