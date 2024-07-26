package com.api.takeiteasystock.application.dto.reqeust


data class UpdateRequestDto (
    val orderId: String? = null,
    val orderOwner: String? = null,
    val shippingAddress: String? = null,
    val shippingMemo: String? = null,
    val products: List<Product>,
){
    data class Product(val productId: String,val productName:String, val quantity: Int )
}
