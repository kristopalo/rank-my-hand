package com.bigstackbully.rankmyhand.controller.advice

import com.bigstackbully.rankmyhand.model.error.ErrorMessage
import com.bigstackbully.rankmyhand.model.exception.EnumNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(EnumNotFoundException::class)
    fun handleEnumNotFoundException(enfe: EnumNotFoundException): ResponseEntity<ErrorMessage> {
        val httpStatus = HttpStatus.NOT_FOUND
        val errorMessage = ErrorMessage(
            httpStatus = httpStatus.value(),
            errorMessage = enfe.message
        )

        return ResponseEntity(errorMessage, httpStatus)
    }
}