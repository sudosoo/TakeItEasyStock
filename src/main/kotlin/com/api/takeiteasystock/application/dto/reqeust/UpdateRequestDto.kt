package com.api.takeiteasystock.application.dto.reqeust

class UpdateRequestDto (
    val orderOwner: String? = null,
    val shippingAddress: String? = null,
    val shippingMemo: String? = null,
    val product: List<Product>,
)
{
    class Product(
        val productId: String,
        val quantity: Int
    )
}
