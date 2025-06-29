package com.bigstackbully.rankmyhand.model.error

data class ErrorMessage(
    val httpStatus: Int?,
    val errorMessage: String?
)
