package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.dto.CardRankDto
import com.bigstackbully.rankmyhand.model.response.GetAllCardRanksResponse
import com.bigstackbully.rankmyhand.service.CardRanksService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/card-ranks")
class CardRanksController(
    private val cardRanksService: CardRanksService
) {

    @CrossOrigin(origins = [])
    @GetMapping()
    fun getAllRankings(): GetAllCardRanksResponse {
        val cardRanks = cardRanksService.getAllCardRanks()
            .sortedByDescending { it.value }
            .map { cr ->
                with(cr) {
                    CardRankDto(
                        key = cr.key,
                        name = cr.name,
                        value = cr.value
                    )
                }
            }

        return GetAllCardRanksResponse(
            cardRanks = cardRanks
        )
    }
}