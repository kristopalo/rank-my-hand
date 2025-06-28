package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.dto.PlayingCardDto
import com.bigstackbully.rankmyhand.model.response.GetAllCardsResponse
import com.bigstackbully.rankmyhand.service.PlayingCardService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/cards")
class CardsController(
    private val cardService: PlayingCardService
) {

    @CrossOrigin(origins = [])
    @GetMapping()
    fun getAllCards(): GetAllCardsResponse {
        val cardDtos = cardService.getAllCards()
            .map { pc ->
                PlayingCardDto(
                    code = pc.standardNotation,
                    rank = pc.rank.name,
                    suit = pc.suit.name,
                    enumName = pc.name,
                    displayName = pc.displayName,
                    value = pc.rank.value,
                )
            }

        return GetAllCardsResponse(
            cards = cardDtos
        )
    }
}