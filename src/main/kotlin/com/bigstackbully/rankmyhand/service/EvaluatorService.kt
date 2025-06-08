package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.RankUnit
import com.bigstackbully.rankmyhand.model.command.EvaluateHandCommand
import com.bigstackbully.rankmyhand.model.EvaluationResult
import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.HandRank
import com.bigstackbully.rankmyhand.service.utils.areInConsecutiveDescOrder
import com.bigstackbully.rankmyhand.service.utils.areSuited
import com.bigstackbully.rankmyhand.service.utils.highestRank
import com.bigstackbully.rankmyhand.service.utils.maxUnitSize
import org.springframework.stereotype.Service
import java.util.SortedSet

@Service
class EvaluatorService {

    fun evaluate(evaluateHandCmd: EvaluateHandCommand): EvaluationResult {
        val hand = Hand(
            cards = evaluateHandCmd.cards
        )
        return evaluate(hand)
    }

    fun evaluate(hand: Hand): EvaluationResult {
        val result: EvaluationResult

        val rankUnits = hand.cards
            .groupBy { it.rank }
            .map { (_, cards) -> RankUnit.of(cards) }
            .toSortedSet()

        val handRank = when (rankUnits.size) {
            2 -> handleTwoRankUnits(rankUnits)
            3 -> handleThreeRankUnits(rankUnits)
            4 -> handleFourRankUnits(rankUnits)
            5 -> handleFiveRankUnits(rankUnits)
            else -> error("The number of rank units has to be between 2 and 5, inclusive.")
        }

        return EvaluationResult(
            hand = hand.cards.joinToString(separator = " ") { it.abbreviation },
            rank = handRank
        )
    }

    private fun handleTwoRankUnits(rankUnits: SortedSet<RankUnit>): HandRank {
        if (rankUnits.maxUnitSize() == 4)
            return HandRank.FOUR_OF_A_KIND

        return HandRank.FULL_HOUSE
    }

    private fun handleThreeRankUnits(rankUnits: SortedSet<RankUnit>): HandRank {
        if (rankUnits.maxUnitSize() == 3)
            return HandRank.THREE_OF_A_KIND

        return HandRank.TWO_PAIR
    }

    private fun handleFourRankUnits(rankUnits: SortedSet<RankUnit>): HandRank {
        return HandRank.ONE_PAIR
    }

    private fun handleFiveRankUnits(rankUnits: SortedSet<RankUnit>): HandRank {
        if (rankUnits.areSuited()) {
            handleFiveRankUnitsSuited(rankUnits)
        }

        return handleFiveRankUnitsOffsuit(rankUnits)
    }

    private fun handleFiveRankUnitsSuited(rankUnits: SortedSet<RankUnit>): HandRank {
        if (rankUnits.areInConsecutiveDescOrder()) {
            handleFiveRankUnitsSuitedStraight(rankUnits)
        }

        return HandRank.FLUSH
    }

    private fun handleFiveRankUnitsSuitedStraight(rankUnits: SortedSet<RankUnit>): HandRank {
        if (rankUnits.highestRank() == CardRank.ACE)
            return HandRank.ROYAL_FLUSH

        return HandRank.STRAIGHT_FLUSH
    }

    private fun handleFiveRankUnitsOffsuit(rankUnits: SortedSet<RankUnit>): HandRank {
        if (rankUnits.areInConsecutiveDescOrder())
            return HandRank.STRAIGHT

        return HandRank.HIGH_CARD
    }
}