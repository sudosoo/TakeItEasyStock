package com.api.takeiteasystock.application.service

import com.api.takeiteasystock.application.dto.reqeust.RegisterProductRequestDto
import com.api.takeiteasystock.application.dto.reqeust.UpdateProductRequestDto
import com.api.takeiteasystock.application.dto.reqeust.UpdateRequestDto
import com.api.takeiteasystock.core.util.JpaService
import com.api.takeiteasystock.domain.entity.Stock
import com.api.takeiteasystock.domain.repository.StockRepository
import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.hibernate.query.sqm.tree.SqmNode.log
import org.slf4j.LoggerFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
@Transactional
class StockService(
    private val repository: StockRepository,
):JpaService<Stock, UUID> {

    val log = KotlinLogging.logger {}
    override var jpaRepository: JpaRepository<Stock, UUID> = repository

    fun decrease(requestDto: UpdateRequestDto) {
        requestDto.products.map { item ->
            val stock = repository.findByProductId(UUID.fromString(item.productId))
            stock.quantity = stock.quantity - item.quantity
            saveModel(stock)
            log.info("stock decrease : $stock")
        }
    }

    // 롤백, 주문 취소에 사용되는 메소드
    fun increase(requestDto: UpdateRequestDto) {
        requestDto.products.map { item ->
            val stock = repository.findByProductId(UUID.fromString(item.productId))
            stock.quantity = stock.quantity + item.quantity
            saveModel(stock)
            log.info("stock increase : $stock")
        }
    }

    fun register(req: RegisterProductRequestDto) {
        val stock = req.toEntity()
        saveModel(stock)
    }

    fun update(req: UpdateProductRequestDto) {
        val stock = repository.findByProductId(UUID.fromString(req.productId))
        saveModel(stock)
    }

}