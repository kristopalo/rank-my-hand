package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.EvaluationResult
import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.command.EvaluateHandCommand
import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.HandRanking
import org.springframework.stereotype.Service

@Service
class EvaluatorService(
    private val handStrengthService: HandStrengthService
) {

    fun evaluate(evaluateHandCmd: EvaluateHandCommand): EvaluationResult = evaluate(evaluateHandCmd.hand)

    fun evaluate(hand: Hand): EvaluationResult {
        val ranking = when (hand.rankUnitCount) {
            2 -> evaluateHandWithTwoRankUnits(hand)
            3 -> evaluateHandWithThreeRankUnits(hand)
            4 -> evaluateHandWithFourRankUnits()
            5 -> evaluateHandWithFiveRankUnits(hand)
            else -> error("The number of rank units has to be between 2 and 5, inclusive.")
        }

        val standardNotation = hand.standardNotation
        val rankingWithSerializedValue = "${ranking.strength}-${hand.serializedValue}"
        val shortNotation = hand.shortNotation
        val handStrength = handStrengthService.calculateHandStrength(
            handRanking = ranking,
            shortNotation = shortNotation
        )

        return EvaluationResult(
            hand = standardNotation,
            handRanking = ranking,
            serializedValue = rankingWithSerializedValue,
            shortNotation = shortNotation,
            handStrength = handStrength
        )
    }

    private fun evaluateHandWithTwoRankUnits(hand: Hand): HandRanking {
        if (hand.maxUnitSize == 4)
            return HandRanking.FOUR_OF_A_KIND

        return HandRanking.FULL_HOUSE
    }

    private fun evaluateHandWithThreeRankUnits(hand: Hand): HandRanking {
        if (hand.maxUnitSize == 3)
            return HandRanking.THREE_OF_A_KIND

        return HandRanking.TWO_PAIR
    }

    private fun evaluateHandWithFourRankUnits(): HandRanking {
        return HandRanking.ONE_PAIR
    }

    private fun evaluateHandWithFiveRankUnits(hand: Hand): HandRanking {
        if (hand.isSuited)
            return handleFiveRankUnitsSuited(hand)

        return handleFiveRankUnitsOffsuit(hand)
    }

    private fun handleFiveRankUnitsSuited(hand: Hand): HandRanking {
        if (hand.areCardsInConsecutiveDescOrder)
            return handleFiveRankUnitsSuitedStraight(hand)

        return HandRanking.FLUSH
    }

    private fun handleFiveRankUnitsSuitedStraight(hand: Hand): HandRanking {
        if (hand.highestRank == CardRank.ACE)
            return HandRanking.ROYAL_FLUSH

        return HandRanking.STRAIGHT_FLUSH
    }

    private fun handleFiveRankUnitsOffsuit(hand: Hand): HandRanking {
        if (hand.areCardsInConsecutiveDescOrder)
            return HandRanking.STRAIGHT

        return HandRanking.HIGH_CARD
    }
}