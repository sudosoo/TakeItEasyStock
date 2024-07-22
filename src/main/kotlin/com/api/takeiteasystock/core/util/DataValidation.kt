package com.api.takeiteasystock.core.util

import org.hibernate.exception.DataException

object DataValidation {
    fun checkNotNullData(value : Any?, message : String){
        try{ requireNotNull(value) }
        catch (e : IllegalArgumentException){
            throw IllegalArgumentException("DataException : $message")
        }
    }
}