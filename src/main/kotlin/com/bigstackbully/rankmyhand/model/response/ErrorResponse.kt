package com.bigstackbully.rankmyhand.model.response

import org.springframework.http.HttpStatus

data class ErrorResponse(
    val httpStatus: Int?,
    val errorMessage: String?
) {
    companion object {
        fun of(
            httpStatus: HttpStatus,
            ex: Exception
        ): ErrorResponse {
            return ErrorResponse(
                httpStatus = httpStatus.value(),
                errorMessage = ex.message
            )
        }
    }
}