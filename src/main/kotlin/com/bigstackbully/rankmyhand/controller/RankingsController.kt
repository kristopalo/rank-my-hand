package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.dto.HandCombinationDto
import com.bigstackbully.rankmyhand.model.dto.RankingDto
import com.bigstackbully.rankmyhand.model.enums.Rank
import com.bigstackbully.rankmyhand.model.exception.ITEM_SEPARATOR
import com.bigstackbully.rankmyhand.model.notation.RankNotation
import com.bigstackbully.rankmyhand.service.HandCombinationService
import com.bigstackbully.rankmyhand.service.RankingService
import com.bigstackbully.rankmyhand.utils.EMPTY_STRING
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

    @GetMapping("/{rankingId}/hand-combinations/{rankNotation}")
    fun getHandCombination(
        @PathVariable rankingId: String,
        @PathVariable rankNotation: String
    ): HandCombinationDto {
        val rankNtFormatted = rankNotation.trim().uppercase()

        require(rankNtFormatted.length == 5) {
            "Path parameter 'rankNotation' must contain exactly 5 characters."
        }

        val (validRankKeys, invalidRankKeys) = rankNtFormatted
            .map { key -> key.toString() }
            .partition { Rank.containsKey(it) }

        require(invalidRankKeys.isEmpty()) {
            "Found invalid rank keys in the 'rankNotation' path parameter: [${invalidRankKeys.joinToString(separator = ITEM_SEPARATOR)}]. Supported values are: [${
                Rank.keys().joinToString(separator = ITEM_SEPARATOR)
            }]."
        }

        val validRanksFormatted = validRankKeys.joinToString(separator = EMPTY_STRING)
        val rankNt = RankNotation.from(validRanksFormatted)
        val ranking = rankingService.getRankingByKeyOrName(rankingId)

        val handCombination = handCombinationService.getHandCombination(
            ranking = ranking,
            rankNotation = rankNt
        )

        return HandCombinationDto.of(handCombination)
    }
}