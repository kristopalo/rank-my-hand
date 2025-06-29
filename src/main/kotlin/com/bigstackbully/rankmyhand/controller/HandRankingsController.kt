package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.dto.HandCombinationDto
import com.bigstackbully.rankmyhand.model.dto.HandRankingDto
import com.bigstackbully.rankmyhand.service.HandCombinationService
import com.bigstackbully.rankmyhand.service.RankingService
import org.springframework.web.bind.annotation.*


@CrossOrigin(origins = [])
@RestController
@RequestMapping("/api/hand-rankings")
class HandRankingsController(
    private val rankingService: RankingService,
    private val handCombinationService: HandCombinationService
) {

    @GetMapping()
    fun getAllHandRankings(): List<HandRankingDto> = rankingService.getAllRankingsSortedByStrengthDesc()
        .map { hr -> HandRankingDto.of(hr) }

    @GetMapping("/{rankingId}")
    fun getHandRankingByKeyOrName(@PathVariable rankingId: String): HandRankingDto {
        val handRanking = rankingService.getRankingByKeyOrName(rankingId)
        return HandRankingDto.of(handRanking)
    }

    @GetMapping("/{rankingId}/hand-combinations")
    fun getAllHandCombinations(@PathVariable rankingId: String): List<HandCombinationDto> {
        val handRanking = rankingService.getRankingByKeyOrName(rankingId)
        return handCombinationService.getAllHandCombinationsForRanking(ranking = handRanking)
            .map { hr -> HandCombinationDto.of(hr) }
    }

    @GetMapping("/{rankingId}/hand-combinations/{shortNotation}")
    fun getAllHandCombinations(
        @PathVariable rankingId: String,
        @PathVariable shortNotation: String
    ): HandCombinationDto {
        val handRanking = rankingService.getRankingByKeyOrName(rankingId)
        val handCombination = handCombinationService.getHandCombination(
            ranking = handRanking,
            shortNotation = shortNotation
        )
        return HandCombinationDto.of(handCombination)
    }
}