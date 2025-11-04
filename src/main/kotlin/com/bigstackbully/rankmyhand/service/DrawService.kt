package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.HandContext
import com.bigstackbully.rankmyhand.model.StraightDrawEvaluationResult
import com.bigstackbully.rankmyhand.model.enums.Ranking
import com.bigstackbully.rankmyhand.model.improvement.Draw
import org.springframework.stereotype.Service

@Service
class DrawService(
    private val handCombinationService: HandCombinationService
) {

    fun evaluatePotentialDraws(
        handContext: HandContext
    ): List<Draw> {
        val draws = mutableListOf<Draw>()

        // TODO Kristo @ 04.11.2025 -> isFlushDraw()
        // TODO Kristo @ 04.11.2025 -> isStraightDraw()

        return draws
    }

    fun tryFindAllPossibleStraightDraws(
        handContext: HandContext
    ): List<StraightDrawEvaluationResult> {
        val contextDistinctRankKeys =
            handContext.cards.sortedByDescending { it.rank.value }.map { it.rank.key }.distinct()
        val straightHandCombos = handCombinationService.getAllHandCombinationsForRanking(Ranking.STRAIGHT)

        // TODO Kristo @ 04.11.2025 -> Maybe we could reuse rankCounts here again? How many hits are we getting for each hand combination?

        val numberOfCardsLeftToDraw = handContext.numberOfCardsLeftToDraw

        return straightHandCombos
            .map { hc ->
                val comboStraightRankKeys = hc.hand.toList().map { it.toString() }
                val matchingRankKeys = contextDistinctRankKeys.intersect(comboStraightRankKeys).toList()

                StraightDrawEvaluationResult(
                    handContext = handContext,
                    targetHandCombination = hc,
                    matchingCards = TODO(),
                    outs = TODO()
                )
            }
            .filter { it.numberOfCardsNeeded <= numberOfCardsLeftToDraw }
            .sortedWith(
                compareByDescending<StraightDrawEvaluationResult> { it.numberOfCardsNeeded }
                    .thenByDescending { it.targetHandCombination.absoluteStrength }
            )
    }
}

