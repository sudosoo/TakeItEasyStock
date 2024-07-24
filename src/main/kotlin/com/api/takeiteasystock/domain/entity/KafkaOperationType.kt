package com.api.takeiteasystock.domain.entity

enum class KafkaOperationType() {
    PRODUCT_CREATE,
    PRODUCT_UPDATE,
    ORDER_COMPLETED,
    ORDER_CANCELLED,
}