package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.CardGroup
import com.bigstackbully.rankmyhand.model.command.EvaluateCardsCommand
import com.bigstackbully.rankmyhand.model.EvaluationResult
import com.bigstackbully.rankmyhand.model.EvaluationSet
import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.HandRank
import org.springframework.stereotype.Service

@Service
class EvaluatorService {

    fun evaluate(evaluateCardsCmd: EvaluateCardsCommand): EvaluationResult {
        return EvaluationResult(HandRank.HIGH_CARD)
    }

    fun evaluateHand(hand: Hand): EvaluationResult {
        val result: EvaluationResult

        val cardGroups = hand.cards
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

    fun isRoyalFlush(hand: Hand): Boolean {
        val cards = hand.cards
        val ranks = cards.map { card -> card.rank }

        return hand.numberOfCards == 5 &&
            hand.isSuited &&
            ranks.contains(CardRank.ACE) &&
            ranks.contains(CardRank.KING) &&
            ranks.contains(CardRank.QUEEN) &&
            ranks.contains(CardRank.JACK) &&
            ranks.contains(CardRank.TEN)
    }
}