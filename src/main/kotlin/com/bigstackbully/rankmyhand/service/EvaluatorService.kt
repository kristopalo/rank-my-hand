package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.CardGroup
import com.bigstackbully.rankmyhand.model.command.EvaluateCardsCommand
import com.bigstackbully.rankmyhand.model.EvaluationResult
import com.bigstackbully.rankmyhand.model.EvaluationSet
import com.bigstackbully.rankmyhand.model.FiveCardHand
import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.HandRank
import org.springframework.stereotype.Service

@Service
class EvaluatorService {

    fun evaluate(evaluateCardsCmd: EvaluateCardsCommand): EvaluationResult {
        val availableCards = evaluateCardsCmd.cards.toList()

        val allFiveCardHands = mutableListOf<FiveCardHand>()

        // TODO Kristo @ 07.06.2025 -> Find all possible 5-card combinations out of 7 available cards. Should result to 21 combinations.
        for (i in 0 until availableCards.size - 1) {
            for (j in (i + 1) until availableCards.size) {
                val excludedIndices = setOf(i, j)
                val currentCards = availableCards.filterIndexed { index, _ -> index !in excludedIndices }

                val currentFiveCardHand = FiveCardHand(
                    cards = currentCards
                )

                allFiveCardHands.add(currentFiveCardHand)
            }
        }

        // TODO Kristo @ 07.06.2025 -> Evaluate each possible hand and find out which one of them is the best.

        return EvaluationResult(HandRank.HIGH_CARD)
    }

    fun evaluateHand(fiveCardHand: FiveCardHand): EvaluationResult {
        val result: EvaluationResult

        val cardGroups = fiveCardHand.cards
            .groupBy { it.rank }
            .map { (_, cards) -> CardGroup.of(cards) }
            .toSortedSet()

        val evaluationSet = EvaluationSet(
            cardGroups = cardGroups
        )

        val groupCount = evaluationSet.groupCount

        result = when (groupCount) {
            2 -> handleEvaluationSetOfTwoCardGroups()
            3 -> handleEvaluationSetOfThreeCardGroups()
            4 -> handleEvaluationSetOfFourCardGroups()
            5 -> handleEvaluationSetOfFiveCardGroups()
            else -> EvaluationResult(HandRank.HIGH_CARD) // TODO Kristo @ 04.06.2025 -> Just a hack here.
        }

        return result
    }

    private fun handleEvaluationSetOfFourCardGroups(): EvaluationResult {
        TODO("Not yet implemented")
    }

    private fun handleEvaluationSetOfThreeCardGroups(): EvaluationResult {
        TODO("Not yet implemented")
    }

    private fun handleEvaluationSetOfTwoCardGroups(): EvaluationResult {
        TODO("Not yet implemented")
    }

    private fun handleEvaluationSetOfFiveCardGroups(): EvaluationResult {
        TODO("Not yet implemented")
    }

    fun isRoyalFlush(fiveCardHand: FiveCardHand): Boolean {
        val cards = fiveCardHand.cards
        val ranks = cards.map { card -> card.rank }

        return fiveCardHand.numberOfCards == 5 &&
                fiveCardHand.isSuited &&
                ranks.contains(CardRank.ACE) &&
                ranks.contains(CardRank.KING) &&
                ranks.contains(CardRank.QUEEN) &&
                ranks.contains(CardRank.JACK) &&
                ranks.contains(CardRank.TEN)
    }
}