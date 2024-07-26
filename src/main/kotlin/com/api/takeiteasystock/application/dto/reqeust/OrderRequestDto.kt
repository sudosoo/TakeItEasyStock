package com.api.takeiteasystock.application.dto.reqeust

data class OrderRequestDto(
    val orderId: String,
    val orderer: String,
    val shippingAddress: String,
    val shippingMemo: String,
    val products: List<RegisterProductRequestDto>
)