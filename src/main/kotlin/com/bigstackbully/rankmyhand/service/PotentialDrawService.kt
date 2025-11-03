package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.HandContext
import com.bigstackbully.rankmyhand.model.enums.Ranking
import com.bigstackbully.rankmyhand.model.improvement.PotentialDraw
import org.springframework.stereotype.Service

@Service
class PotentialDrawService(
    private val handCombinationService: HandCombinationService
) {

    fun evaluatePotentialDraws(
        handContext: HandContext
    ): List<PotentialDraw> {
        val potentialDraws = mutableListOf<PotentialDraw>()

        // TODO Kristo @ 04.11.2025 -> isFlushDraw()
        // TODO Kristo @ 04.11.2025 -> isStraightDraw()

        return potentialDraws
    }

    fun evaluateForStraightDraw(
        handContext: HandContext
    ): PotentialDraw? {
        val distinctRankKeys = handContext.cards.map { it.rank.key }.distinct()
        val allStraightHandCombos = handCombinationService.getAllHandCombinationsForRanking(Ranking.STRAIGHT)

        // TODO Kristo @ 04.11.2025 -> Maybe we could reuse rankCounts here again? How many hits are we getting for each hand combination?

        return null
    }
}