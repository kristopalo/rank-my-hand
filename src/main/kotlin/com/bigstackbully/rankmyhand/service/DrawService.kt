package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.EvaluationContext
import com.bigstackbully.rankmyhand.model.StraightDrawEvaluationResult
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.model.enums.Ranking
import com.bigstackbully.rankmyhand.model.improvement.Draw
import org.springframework.stereotype.Service

@Service
class DrawService(
    private val handCombinationService: HandCombinationService
) {

    fun evaluatePotentialDraws(
        evaluationContext: EvaluationContext
    ): List<Draw> {
        val draws = mutableListOf<Draw>()

        // TODO Kristo @ 04.11.2025 -> isFlushDraw()
        // TODO Kristo @ 04.11.2025 -> isStraightDraw()

        return draws
    }

    fun tryFindAllPossibleStraightDraws(
        evalContext: EvaluationContext
    ): List<StraightDrawEvaluationResult> {
        val straightHandCombos = handCombinationService.getAllHandCombinationsForRanking(Ranking.STRAIGHT)

        // TODO Kristo @ 04.11.2025 -> Maybe we could reuse rankCounts here again? How many hits are we getting for each hand combination?

        val numberOfCardsLeftToDraw = evalContext.numberOfCardsLeftToDraw

        return straightHandCombos
            .map { hc ->
                val comboStraightRankKeys = hc.hand.toList().map { it.toString() }

                val matchingCards = mutableListOf<PlayingCard>()
                val cardsLeftToChooseFrom = evalContext.cards.toMutableList()
                val rankKeysWithoutAnyMatches = mutableListOf<String>()

                for (rankKey in comboStraightRankKeys) {
                    val matchingCard = cardsLeftToChooseFrom.find { it.rank.key == rankKey }

                    if (matchingCard != null) {
                        matchingCards.add(matchingCard)
                    } else {
                        rankKeysWithoutAnyMatches.add(rankKey)
                    }
                }

                val outs = rankKeysWithoutAnyMatches.flatMap { missingRankKey ->
                    evalContext.getUndealtCards().filter { it.rank.key == missingRankKey }
                }

                StraightDrawEvaluationResult(
                    evaluationContext = evalContext,
                    targetHandCombination = hc,
                    matchingCards = matchingCards,
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

