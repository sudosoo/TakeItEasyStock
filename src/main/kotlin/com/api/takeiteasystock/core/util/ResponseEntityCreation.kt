package com.api.takeiteasystock.core.util

import ch.qos.logback.core.status.ErrorStatus
import com.api.takeiteasystock.core.common.dto.BaseListRespV2
import com.api.takeiteasystock.core.common.dto.BaseResp
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

    fun getRespEntity(resp : Any?, errorStatus : ErrorStatus = ErrorStatus.): ResponseEntity<BaseResp> {
        return ResponseEntity.ok(BaseResp(resp))
    }
}