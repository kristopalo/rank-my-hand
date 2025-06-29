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

    @CrossOrigin(origins = [])
    @GetMapping()
    fun getAllSuits(): GetAllSuitsResponse = GetAllSuitsResponse(
        suits = suitService.getAllSuits()
            .sortedBy { it.ordinal }
            .map { s ->
                with(s) {
                    SuitDto(
                        key = key,
                        name = name,
                        standardNotation = standardNotation,
                        emoji = emoji
                    )
                }
            }
    )
}