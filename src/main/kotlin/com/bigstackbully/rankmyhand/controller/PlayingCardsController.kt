package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.dto.PlayingCardDto
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.model.response.GetAllPlayingCardsResponse
import com.bigstackbully.rankmyhand.service.PlayingCardService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/playing-cards")
class PlayingCardsController(
    private val cardService: PlayingCardService
) {

    @CrossOrigin(origins = [])
    @GetMapping()
    fun getAllCards(): GetAllPlayingCardsResponse {
        val cards = cardService.getAllCards()
            .sortedWith(
                compareBy<PlayingCard> { it.suit.ordinal }
                    .thenByDescending { it.rank.value }
            )
            .map { pc ->
                with(pc) {
                    PlayingCardDto(
                        key = key,
                        name = name,
                        rank = rank.name,
                        rankKey = rank.key,
                        rankValue = rank.value,
                        suit = suit.name,
                        suitKey = suit.key,
                        suitEmoji = suit.emoji,
                        displayName = displayName,
                        standardNotation = standardNotation
                    )
                }
            }

        return GetAllPlayingCardsResponse(
            playingCards = cards
        )
    }
}