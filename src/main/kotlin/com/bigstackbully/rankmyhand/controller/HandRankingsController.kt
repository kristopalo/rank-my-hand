package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.dto.HandRankingDto
import com.bigstackbully.rankmyhand.model.response.GetAllRankingsResponse
import com.bigstackbully.rankmyhand.service.RankingService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/hand-rankings")
class HandRankingsController(
    private val rankingService: RankingService
) {

    @CrossOrigin(origins = [])
    @GetMapping()
    fun getAllRankings(): GetAllRankingsResponse {
        val rankings = rankingService.getAllRankings()
            .sortedByDescending { it.strength }
            .map { r ->
                HandRankingDto(
                    key = r.key,
                    name = r.name,
                    displayName = r.displayName,
                    strength = r.strength
                )
            }

        return GetAllRankingsResponse(
            handRankings = rankings
        )
    }
}