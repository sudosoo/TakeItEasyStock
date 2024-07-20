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
    fun updateStock(requestDto: UpdateRequestDto) {
        val stock = repository.findByProductId(UUID.fromString(requestDto.productId))
        stock.quantity = requestDto.quantity
        saveModel(stock)
    }



}