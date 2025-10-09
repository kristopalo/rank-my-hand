package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.RankGroup
import com.bigstackbully.rankmyhand.model.dto.HandCombinationDto
import com.bigstackbully.rankmyhand.model.dto.RankingDto
import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.exception.ITEM_SEPARATOR
import com.bigstackbully.rankmyhand.service.HandCombinationService
import com.bigstackbully.rankmyhand.service.RankingService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/rankings")
class RankingsController(
    private val rankingService: RankingService,
    private val handCombinationService: HandCombinationService
) {

    @GetMapping()
    fun getAllRankings(): List<RankingDto> = rankingService.getAllRankingsSortedByStrengthInDescOrder()
        .map { hr -> RankingDto.of(hr) }

    @GetMapping("/{rankingId}")
    fun getRankingByKeyOrName(@PathVariable rankingId: String): RankingDto {
        val ranking = rankingService.getRankingByKeyOrName(rankingId)
        return RankingDto.of(ranking)
    }

    @GetMapping("/{rankingId}/hand-combinations")
    fun getAllHandCombinationsForRanking(@PathVariable rankingId: String): List<HandCombinationDto> {
        val ranking = rankingService.getRankingByKeyOrName(rankingId)
        return handCombinationService.getAllHandCombinationsForRanking(ranking = ranking)
            .map { hr -> HandCombinationDto.of(hr) }
    }

    @GetMapping("/{rankingId}/hand-combinations/{shortNotation}")
    fun getHandCombination(
        @PathVariable rankingId: String,
        @PathVariable shortNotation: String
    ): HandCombinationDto {
        val shorthandFormatted = shortNotation.trim().uppercase()

        require(shorthandFormatted.length == 5) {
            "Path parameter 'shortNotation' must contain exactly 5 characters."
        }

        val (validRankKeys, invalidRankKeys) = shorthandFormatted
            .map { key -> key.toString() }
            .partition { CardRank.containsKey(it) }

        require(invalidRankKeys.isEmpty()) {
            "Found invalid rank keys in the 'shortNotation' path parameter: [${invalidRankKeys.joinToString(separator = ITEM_SEPARATOR)}]. Supported keys: [${
                CardRank.keys().joinToString(separator = ITEM_SEPARATOR)
            }]."
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

        val ranking = rankingService.getRankingByKeyOrName(rankingId)

        val handCombination = handCombinationService.getHandCombination(
            ranking = ranking,
            rankGroups = rankGroups
        )

        return HandCombinationDto.of(handCombination)
    }
}