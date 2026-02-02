package com.bigstackbully.rankmyhand.controller.advice

import com.bigstackbully.rankmyhand.model.exception.EnumNotFoundException
import com.bigstackbully.rankmyhand.model.response.ErrorResponse
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

private val logger = KotlinLogging.logger {}

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(iae: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        logger.warn { iae }
        return composeResponseEntity(httpStatus = HttpStatus.BAD_REQUEST, ex = iae)
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(ise: IllegalStateException): ResponseEntity<ErrorResponse> {
        logger.warn { ise }
        return composeResponseEntity(httpStatus = HttpStatus.BAD_REQUEST, ex = ise)
    }

    @ExceptionHandler(EnumNotFoundException::class)
    fun handleEnumNotFoundException(infe: EnumNotFoundException): ResponseEntity<ErrorResponse> {
        logger.warn { infe }
        return composeResponseEntity(httpStatus = HttpStatus.NOT_FOUND, ex = infe)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
        logger.error(ex) { "Unexpected error occurred" }
        return composeResponseEntity(httpStatus = HttpStatus.INTERNAL_SERVER_ERROR, ex = ex)
    }

    private fun composeResponseEntity(httpStatus: HttpStatus, ex: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse.of(httpStatus = httpStatus, ex = ex)
        return ResponseEntity(errorResponse, httpStatus)
    }
}