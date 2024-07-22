package com.api.takeiteasystock.domain.repository

import com.api.takeiteasystock.domain.entity.Stock
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface StockRepository :JpaRepository<Stock, UUID> {
    fun findByProductId(productId: UUID): Stock

}