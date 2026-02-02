package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.dto.HandCombinationDto
import com.bigstackbully.rankmyhand.model.dto.RankingDto
import com.bigstackbully.rankmyhand.model.enums.Rank
import com.bigstackbully.rankmyhand.model.notation.RankNotation
import com.bigstackbully.rankmyhand.service.HandCombinationService
import com.bigstackbully.rankmyhand.service.RankingService
import com.bigstackbully.rankmyhand.utils.EMPTY_STRING
import com.bigstackbully.rankmyhand.utils.ITEM_SEPARATOR
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/rankings")
@Tag(name = "Rankings", description = "Poker hand rankings and combinations API")
class RankingsController(
    private val rankingService: RankingService,
    private val handCombinationService: HandCombinationService
) {

    @GetMapping()
    @Operation(
        summary = "Get all poker hand rankings",
        description = "Returns all poker hand rankings sorted by strength in descending order (Royal Flush to High Card)"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved all rankings"
    )
    fun getAllRankings(): List<RankingDto> = rankingService.getAllRankingsSortedByStrengthInDescOrder()
        .map { hr -> RankingDto.of(hr) }

    @GetMapping("/{rankingId}")
    @Operation(
        summary = "Get a specific ranking",
        description = "Retrieves a poker hand ranking by its key or name (e.g., 'royal_flush', 'straight', 'two_pair')"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved the ranking",
                content = [Content(schema = Schema(implementation = RankingDto::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Ranking not found"
            )
        ]
    )
    fun getRankingByKeyOrName(
        @Parameter(description = "Ranking identifier (key or name)", example = "royal_flush")
        @PathVariable rankingId: String
    ): RankingDto {
        val ranking = rankingService.getRankingByKeyOrName(rankingId)
        return RankingDto.of(ranking)
    }

    @GetMapping("/{rankingId}/hand-combinations")
    @Operation(
        summary = "Get all hand combinations for a ranking",
        description = "Returns all possible hand combinations for a specific ranking"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved hand combinations"
            ),
            ApiResponse(
                responseCode = "404",
                description = "Ranking not found"
            )
        ]
    )
    fun getAllHandCombinationsForRanking(
        @Parameter(description = "Ranking identifier (key or name)", example = "full_house")
        @PathVariable rankingId: String
    ): List<HandCombinationDto> {
        val ranking = rankingService.getRankingByKeyOrName(rankingId)
        return handCombinationService.getAllHandCombinationsForRanking(ranking = ranking)
            .map { hr -> HandCombinationDto.of(hr) }
    }

    @GetMapping("/{rankingId}/hand-combinations/{rankNotation}")
    @Operation(
        summary = "Get a specific hand combination",
        description = "Retrieves a specific hand combination for a ranking using rank notation (5 characters representing ranks)"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved the hand combination",
                content = [Content(schema = Schema(implementation = HandCombinationDto::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid rank notation format"
            ),
            ApiResponse(
                responseCode = "404",
                description = "Ranking or hand combination not found"
            )
        ]
    )
    fun getHandCombination(
        @Parameter(description = "Ranking identifier (key or name)", example = "full_house")
        @PathVariable rankingId: String,
        @Parameter(description = "Rank notation (5 characters)", example = "AAAKK")
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
            rankNotation = rankNt.toString()
        )

        return HandCombinationDto.of(handCombination)
    }
}