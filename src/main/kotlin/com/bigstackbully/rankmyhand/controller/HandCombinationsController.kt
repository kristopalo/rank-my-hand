package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.dto.HandCombinationDto
import com.bigstackbully.rankmyhand.service.HandCombinationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/hand-combinations")
@Tag(name = "Hand Combinations", description = "Poker hand combination information API")
class HandCombinationsController(
    private val handCombinationService: HandCombinationService
) {

    @GetMapping
    @Operation(
        summary = "Get all hand combinations",
        description = "Returns all possible poker hand combinations across all rankings"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved all hand combinations"
    )
    fun getAllHandCombinations(): List<HandCombinationDto> {
        return handCombinationService.getAllHandCombinations().map { HandCombinationDto.of(it) }
    }
}