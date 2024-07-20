package com.api.takeiteasystock.core.common.dto

import com.api.takeiteasystock.core.common.enums.ErrorStatus

class BaseListRespV2(
    content : MutableList<Any?>,
    totalElements: Long,
    size: Int,
    number: Int,
    errorStatus: ErrorStatus = ErrorStatus.OK
){
    val data: MutableList<Any?> = content

    val total: Long = totalElements

    val size: Int = size

    val page: Int = number

    var status: String? = errorStatus.name

    var message: String? = errorStatus.message
}
