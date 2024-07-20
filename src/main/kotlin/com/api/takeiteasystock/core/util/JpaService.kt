package com.api.takeiteasystock.core.util

import com.api.takeiteasystock.core.util.DataValidation.checkNotNullData
import org.springframework.data.jpa.repository.JpaRepository

interface JpaService<MODEL, ID> {
    var jpaRepository : JpaRepository<MODEL, ID>

    fun saveModel(model: MODEL) : MODEL {
        return jpaRepository.save(model as (MODEL & Any))
    }

    fun saveModels(models: List<MODEL>) : List<MODEL> {
        return jpaRepository.saveAll(models)
    }

    fun findModelById(id : ID) : MODEL {
        checkNotNullData(id, "data is not found by id : $id")
        //TODO 커스텀 에러 메시지로 변경
        return jpaRepository.findById(id!!).orElseThrow { IllegalArgumentException("data is not found by id : $id") }
    }

    fun deleteById(id : ID) {
        checkNotNullData(id, "deleteById : id is null")
        return jpaRepository.deleteById(id!!)
    }


}