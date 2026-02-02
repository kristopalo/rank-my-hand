package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.dto.SuitDto
import com.bigstackbully.rankmyhand.model.response.GetAllSuitsResponse
import com.bigstackbully.rankmyhand.service.SuitService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/suits")
@Tag(name = "Suits", description = "Card suit information API")
class SuitsController(
    private val suitService: SuitService
) {

    @GetMapping
    @Operation(
        summary = "Get all card suits",
        description = "Returns all card suits (Spades, Hearts, Diamonds, Clubs)"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved all suits",
        content = [Content(schema = Schema(implementation = GetAllSuitsResponse::class))]
    )
    fun getAllSuits(): GetAllSuitsResponse = GetAllSuitsResponse(
        suits = suitService.getAllSuits()
            .sortedBy { it.ordinal }
            .map { SuitDto.of(it) }
    )
}