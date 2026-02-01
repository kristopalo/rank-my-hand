package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.dto.CardRankDto
import com.bigstackbully.rankmyhand.model.response.GetAllCardRanksResponse
import com.bigstackbully.rankmyhand.service.CardRankService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/card-ranks")
@Tag(name = "Card Ranks", description = "Card rank information API")
class CardRanksController(
    private val cardRankService: CardRankService
) {

    @GetMapping()
    @Operation(
        summary = "Get all card ranks",
        description = "Returns all card ranks (Ace through Two) sorted by value in descending order"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved all card ranks",
        content = [Content(schema = Schema(implementation = GetAllCardRanksResponse::class))]
    )
    fun getAllCardRanks(): GetAllCardRanksResponse = GetAllCardRanksResponse(
        cardRanks = cardRankService.getAllCardRanks()
            .sortedByDescending { it.value }
            .map { CardRankDto.of(it) }
    )
}