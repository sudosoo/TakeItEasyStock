package com.api.takeiteasystock.core.util

import com.api.takeiteasystock.core.common.dto.BaseListRespV2
import com.api.takeiteasystock.core.common.dto.BaseResp
import com.api.takeiteasystock.core.common.enums.ErrorStatus
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity

interface ResponseEntityCreation {
    fun getListRespEntity(page: Page<*>): ResponseEntity<BaseListRespV2> {
        return ResponseEntity.ok(
            BaseListRespV2(
                content = page.content.toMutableList(),
                totalElements = page.totalElements,
                size = page.size,
                number = page.number
            )
        )
    }

    fun getRespEntity(resp : Any?, errorStatus : ErrorStatus = ErrorStatus.OK): ResponseEntity<BaseResp> {
        return ResponseEntity.ok(BaseResp(resp))
    }
}