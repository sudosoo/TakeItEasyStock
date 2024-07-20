package com.api.takeiteasystock.core.common.dto

import com.api.takeiteasystock.core.common.enums.ErrorStatus
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class BaseResp(
    data : Any?,
    errorStatus : ErrorStatus = ErrorStatus.OK,
    errMessage : String? = null,
) {
    var data : Any? = data

    var status : String = errorStatus.name

    var message : String = errorStatus.message

    var errMessage : String? = errMessage
}