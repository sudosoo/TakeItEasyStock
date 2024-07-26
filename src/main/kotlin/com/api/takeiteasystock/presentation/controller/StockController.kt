package com.api.takeiteasystock.presentation.controller

import com.api.takeiteasystock.application.dto.reqeust.RegisterProductRequestDto
import com.api.takeiteasystock.application.service.StockService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class StockController (
    val service : StockService,
) {
    @PostMapping("/registerProduct")
    fun registerProduct(@RequestBody req : RegisterProductRequestDto){
        service.register(req)
    }

    fun updateStock() {}


}