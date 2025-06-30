package com.bigstackbully.rankmyhand.controller.advice

import com.bigstackbully.rankmyhand.model.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(iae: IllegalArgumentException): ResponseEntity<ErrorResponse> =
        composeResponseEntity(httpStatus = HttpStatus.NOT_FOUND, iae)

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> =
        composeResponseEntity(httpStatus = HttpStatus.INTERNAL_SERVER_ERROR, ex = ex)

    fun composeResponseEntity(httpStatus: HttpStatus, ex: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse.of(httpStatus = httpStatus, ex = ex)
        return ResponseEntity(errorResponse, httpStatus)
    }
}