package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.dto.CardRankDto
import com.bigstackbully.rankmyhand.model.response.GetAllCardRanksResponse
import com.bigstackbully.rankmyhand.service.CardRankService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/card-ranks")
class CardRanksController(
    private val cardRankService: CardRankService
) {

    @GetMapping()
    fun getAllCardRanks(): GetAllCardRanksResponse = GetAllCardRanksResponse(
        cardRanks = cardRankService.getAllCardRanks()
            .sortedByDescending { it.value }
            .map { CardRankDto.of(it) }
    )
}