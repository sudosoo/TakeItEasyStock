package com.api.takeiteasystock.application.dto.reqeust

import com.api.takeiteasystock.domain.entity.Stock
import java.util.*

data class RegisterProductRequestDto (
    val productId: String,
    val productName:String,
    val quantity: Int
){
    fun toEntity(): Stock {
        return Stock(
            productId = UUID.fromString(productId),
            productName = productName,
            quantity = quantity
        )
    }
}