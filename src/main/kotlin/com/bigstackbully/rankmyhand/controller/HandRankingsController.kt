package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.RankGroup
import com.bigstackbully.rankmyhand.model.dto.HandCombinationDto
import com.bigstackbully.rankmyhand.model.dto.HandRankingDto
import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.exception.ITEM_SEPARATOR
import com.bigstackbully.rankmyhand.service.HandCombinationService
import com.bigstackbully.rankmyhand.service.RankingService
import org.springframework.web.bind.annotation.*


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
        val shortNotationFormatted = shortNotation.trim().uppercase()

        require(shortNotationFormatted.length == 5) {
            "Path parameter 'shortNotation' must contain exactly 5 characters."
        }

        val (validRankKeys, invalidRankKeys) = shortNotationFormatted
            .map { key -> key.toString() }
            .partition { CardRank.containsKey(it) }

        require(invalidRankKeys.isEmpty()) {
            "Found invalid rank keys in the 'shortNotation' path parameter: [${invalidRankKeys.joinToString(separator = ITEM_SEPARATOR)}]. Supported keys: [${CardRank.keys().joinToString(separator = ITEM_SEPARATOR)}]."
        }

        // TODO Kristo @ 30.06.2025 -> Normalize the shortNotation
        val rankGroups = validRankKeys
            .map { key -> CardRank.fromKeyOrThrow(key) }
            .groupBy { it }
            .map { (_, ranks) -> RankGroup(ranks = ranks) }
            .sortedWith(
                compareByDescending<RankGroup> { it.groupSize }
                    .thenByDescending { it.totalValue }
            )

        val handRanking = rankingService.getRankingByKeyOrName(rankingId)
        val handCombination = handCombinationService.getHandCombination(
            ranking = handRanking,
            rankGroups = rankGroups
        )

        return HandCombinationDto.of(handCombination)
    }
}