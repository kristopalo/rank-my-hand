package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.dto.CardDto
import com.bigstackbully.rankmyhand.model.enums.Card
import com.bigstackbully.rankmyhand.model.response.GetAllCardsResponse
import com.bigstackbully.rankmyhand.service.CardService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/cards")
@Tag(name = "Cards", description = "Card information API")
class CardsController(
    private val cardService: CardService
) {
    @GetMapping
    @Operation(
        summary = "Get all cards",
        description = "Returns a list of all 52 playing cards sorted by suit and rank"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved all cards",
        content = [Content(schema = Schema(implementation = GetAllCardsResponse::class))]
    )
    fun getAllCards(): GetAllCardsResponse = GetAllCardsResponse(
        cards = cardService.getAllCards()
            .sortedWith(compareBy<Card> { it.suit.ordinal }.thenByDescending { it.rank.value })
            .map { CardDto.of(it) }
    )
}