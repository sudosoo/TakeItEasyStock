package com.api.takeiteasystock.application.service

import com.api.takeiteasystock.application.dto.reqeust.IncreaseRequestDto
import com.api.takeiteasystock.domain.repository.StockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StockService(
    private val stockRepository: StockRepository
) {
    @Transactional
    fun increaseStock(requestDto: IncreaseRequestDto) {
        val stock = stockRepository.findByProductId(productId)
        stock.quantity = stock.quantity + quantity
        stockRepository.save(stock)
    }
    @Transactional
    fun decreaseStock(productId: String, quantity: Int) {
        val stock = stockRepository.findByProductId(productId)
        stock.quantity = stock.quantity - quantity
        stockRepository.save(stock)
    }
}