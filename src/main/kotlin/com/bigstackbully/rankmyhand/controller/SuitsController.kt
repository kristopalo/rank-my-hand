package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.dto.SuitDto
import com.bigstackbully.rankmyhand.model.response.GetAllSuitsResponse
import com.bigstackbully.rankmyhand.service.SuitService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/suits")
class SuitsController(
    private val suitService: SuitService
) {

    @GetMapping()
    fun getAllSuits(): GetAllSuitsResponse = GetAllSuitsResponse(
        suits = suitService.getAllSuits()
            .sortedBy { it.ordinal }
            .map { SuitDto.of(it) }
    )
}