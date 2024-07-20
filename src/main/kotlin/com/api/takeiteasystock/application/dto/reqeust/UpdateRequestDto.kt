package com.api.takeiteasystock.application.dto.reqeust

class UpdateRequestDto (
    val orderId: String? = null,
    val shippingAddress: String? = null,
    val customerName: String? = null,
    val orderItem: List<OrderItem>,
)
{
    class OrderItem(
        val productId: String,
        val quantity: Int
    )
}
