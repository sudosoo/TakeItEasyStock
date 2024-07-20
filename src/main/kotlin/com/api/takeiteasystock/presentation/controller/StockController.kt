package com.api.takeiteasystock.presentation.controller

import com.api.takeiteasystock.application.service.StockService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class StockController (
    val service : StockService,

) {

    fun syncStock() {
    //TODO 상품 서비스 api 로 상품 정보를 가져와서 재고를 동기화 하는 로직을 작성해주세요.
    }

    fun updateStock() {}


}