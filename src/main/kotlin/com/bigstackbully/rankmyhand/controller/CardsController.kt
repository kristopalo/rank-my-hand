package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.dto.PlayingCardDto
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.model.response.GetAllCardsResponse
import com.bigstackbully.rankmyhand.service.PlayingCardService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/playing-cards")
class CardsController(
    private val cardService: PlayingCardService
) {

    @CrossOrigin(origins = [])
    @GetMapping()
    fun getAllCards(): GetAllCardsResponse {
        val cards = cardService.getAllCards()
            .sortedWith(
                compareBy<PlayingCard> { it.suit.ordinal }
                    .thenByDescending { it.rank.value }
            )
            .map { pc ->
                PlayingCardDto(
                    code = pc.standardNotation,
                    enum = pc.name,
                    rank = pc.rank.name,
                    value = pc.rank.value,
                    suit = pc.suit.name,
                    suitEmoji = pc.suit.emoji,
                    displayName = pc.displayName
                )
            }

        return GetAllCardsResponse(
            cards = cards
        )
    }
}