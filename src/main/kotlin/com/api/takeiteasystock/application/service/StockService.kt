package com.api.takeiteasystock.application.service

import com.api.takeiteasystock.application.dto.reqeust.UpdateRequestDto
import com.api.takeiteasystock.core.util.JpaService
import com.api.takeiteasystock.domain.entity.Stock
import com.api.takeiteasystock.domain.repository.StockRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class StockService(
    private val repository: StockRepository

):JpaService<Stock, UUID> {

    override var jpaRepository: JpaRepository<Stock, UUID> = repository

    @Transactional
    fun decrease(requestDto: UpdateRequestDto) {

        requestDto.orderItem.map { item ->
            val stock = repository.findByProductId(UUID.fromString(item.productId))
            stock.quantity.minus(item.quantity)
            saveModel(stock)
        }
    }

    // 롤백, 주문 취소에 사용되는 메소드
    @Transactional
    fun increase(requestDto: UpdateRequestDto) {

        requestDto.orderItem.map { item ->
            val stock = repository.findByProductId(UUID.fromString(item.productId))
            stock.quantity.plus(item.quantity)
            saveModel(stock)
        }
    }



}