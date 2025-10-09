package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.dto.HandCombinationDto
import com.bigstackbully.rankmyhand.service.HandCombinationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/hand-combinations")
class HandCombinationsController(
    private val handCombinationService: HandCombinationService
) {

    @GetMapping()
    fun getAllHandCombinations(): List<HandCombinationDto> {
        return handCombinationService.getAllHandCombinations().map { hr -> HandCombinationDto.of(hr) }
    }

//    @GetMapping("/{shortNotation}")
//    fun getHandCombination(
//        @PathVariable shortNotation: String
//    ): HandCombinationDto {
//        val shortNotationFormatted = shortNotation.trim().uppercase()
//
//        require(shortNotationFormatted.length == 5) {
//            "Path parameter 'shortNotation' must contain exactly 5 characters."
//        }
//
//        val (validRankKeys, invalidRankKeys) = shortNotationFormatted
//            .map { key -> key.toString() }
//            .partition { CardRank.containsKey(it) }
//
//        require(invalidRankKeys.isEmpty()) {
//            "Found invalid rank keys in the 'shortNotation' path parameter: [${invalidRankKeys.joinToString(separator = ITEM_SEPARATOR)}]. Supported keys: [${CardRank.keys().joinToString(separator = ITEM_SEPARATOR)}]."
//        }
//
//        // TODO Kristo @ 30.06.2025 -> Normalize the shortNotation
//        val rankGroups = validRankKeys
//            .map { key -> CardRank.fromKeyOrThrow(key) }
//            .groupBy { it }
//            .map { (_, ranks) -> RankGroup(ranks = ranks) }
//            .sortedWith(
//                compareByDescending<RankGroup> { it.groupSize }
//                    .thenByDescending { it.totalValue }
//            )
//
//        val handRanking = rankingService.getRankingByKeyOrName(rankingId)
//        val handCombination = handCombinationService.getHandCombination(
//            ranking = handRanking,
//            rankGroups = rankGroups
//        )
//
//        return HandCombinationDto.of(handCombination)
//    }
}