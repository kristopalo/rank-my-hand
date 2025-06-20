package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.RankUnit
import com.bigstackbully.rankmyhand.model.command.EvaluateHandCommand
import com.bigstackbully.rankmyhand.model.EvaluationResult
import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.HandRanking
import com.bigstackbully.rankmyhand.service.utils.areInConsecutiveDescOrder
import com.bigstackbully.rankmyhand.service.utils.areSuited
import com.bigstackbully.rankmyhand.service.utils.highestRank
import com.bigstackbully.rankmyhand.service.utils.areWheelStraight
import com.bigstackbully.rankmyhand.service.utils.maxUnitSize
import com.bigstackbully.rankmyhand.service.utils.rankingWithSerializedValue
import com.bigstackbully.rankmyhand.service.utils.shortNotation
import com.bigstackbully.rankmyhand.service.utils.standardNotation
import org.springframework.stereotype.Service
import java.util.SortedSet

@Service
class EvaluatorService(
    private val handStrengthService: HandStrengthService
) {

    fun evaluate(evaluateHandCmd: EvaluateHandCommand): EvaluationResult = evaluate(evaluateHandCmd.hand)

    fun evaluate(hand: Hand): EvaluationResult {
        val rankUnits = hand.cards
            .groupBy { it.rank }
            .map { (_, cards) -> RankUnit.of(cards) }
            .toSortedSet()

        // TODO Kristo @ 20.06.2025 -> We should inject hand here instead, not a sortedSet of rankUnits. A Hand should contain them internally.
        val handRanking = when (rankUnits.size) {
            2 -> handleHandWithTwoRankUnits(rankUnits)
            3 -> handleHandWithThreeRankUnits(rankUnits)
            4 -> handleHandWithFourRankUnits(rankUnits)
            5 -> handleHandWithFiveRankUnits(rankUnits)
            else -> error("The number of rank units has to be between 2 and 5, inclusive.")
        }

        val standardNotation = rankUnits.standardNotation()
        val rankingWithSerializedValue = rankUnits.rankingWithSerializedValue(handRanking)
        val shortNotation = rankUnits.shortNotation()
        val handStrength = handStrengthService.calculateHandStrength(handRanking, shortNotation)

        return EvaluationResult(
            hand = standardNotation,
            handRanking = handRanking,
            serializedValue = rankingWithSerializedValue,
            shortNotation = shortNotation,
            absolutePosition = handStrength.absolutePosition,
            absoluteStrength = handStrength.absoluteStrength,
            relativePosition = handStrength.relativePosition,
            relativeStrength = handStrength.relativeStrength
        )
    }

    private fun handleHandWithTwoRankUnits(rankUnits: SortedSet<RankUnit>): HandRanking {
        if (rankUnits.maxUnitSize() == 4)
            return HandRanking.FOUR_OF_A_KIND

        return HandRanking.FULL_HOUSE
    }

    private fun handleHandWithThreeRankUnits(rankUnits: SortedSet<RankUnit>): HandRanking {
        if (rankUnits.maxUnitSize() == 3)
            return HandRanking.THREE_OF_A_KIND

        return HandRanking.TWO_PAIR
    }

    private fun handleHandWithFourRankUnits(rankUnits: SortedSet<RankUnit>): HandRanking {
        return HandRanking.ONE_PAIR
    }

    private fun handleHandWithFiveRankUnits(rankUnits: SortedSet<RankUnit>): HandRanking {
        if (rankUnits.areSuited())
            return handleFiveRankUnitsSuited(rankUnits)

        return handleFiveRankUnitsOffsuit(rankUnits)
    }

    private fun handleFiveRankUnitsSuited(rankUnits: SortedSet<RankUnit>): HandRanking {
        if (rankUnits.areInConsecutiveDescOrder())
            return handleFiveRankUnitsSuitedStraight(rankUnits)

        return HandRanking.FLUSH
    }

    private fun handleFiveRankUnitsSuitedStraight(rankUnits: SortedSet<RankUnit>): HandRanking {
        if (rankUnits.highestRank() == CardRank.ACE)
            return HandRanking.ROYAL_FLUSH

        return HandRanking.STRAIGHT_FLUSH
    }

    private fun handleFiveRankUnitsOffsuit(rankUnits: SortedSet<RankUnit>): HandRanking {
        if (rankUnits.areInConsecutiveDescOrder())
            return HandRanking.STRAIGHT

        return HandRanking.HIGH_CARD
    }
}