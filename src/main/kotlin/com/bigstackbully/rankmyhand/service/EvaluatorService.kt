package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.RankUnit
import com.bigstackbully.rankmyhand.model.command.EvaluateHandCommand
import com.bigstackbully.rankmyhand.model.EvaluationResult
import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.HandRanking
import com.bigstackbully.rankmyhand.service.utils.areInConsecutiveDescOrder
import com.bigstackbully.rankmyhand.service.utils.areSuited
import com.bigstackbully.rankmyhand.service.utils.valueEncoded
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
            handRanking = handRank,
            serializedValue = "${handRank.strength}-${rankUnits.valueEncoded()}",
            shortNotation = rankUnits.joinToString(separator = "") { it.ranksInStandardNotation }
        )
    }

    private fun handleTwoRankUnits(rankUnits: SortedSet<RankUnit>): HandRanking {
        if (rankUnits.maxUnitSize() == 4)
            return HandRanking.FOUR_OF_A_KIND

        return HandRanking.FULL_HOUSE
    }

    private fun handleThreeRankUnits(rankUnits: SortedSet<RankUnit>): HandRanking {
        if (rankUnits.maxUnitSize() == 3)
            return HandRanking.THREE_OF_A_KIND

        return HandRanking.TWO_PAIR
    }

    private fun handleFourRankUnits(rankUnits: SortedSet<RankUnit>): HandRanking {
        return HandRanking.ONE_PAIR
    }

    private fun handleFiveRankUnits(rankUnits: SortedSet<RankUnit>): HandRanking {
        if (rankUnits.areSuited()) {
            handleFiveRankUnitsSuited(rankUnits)
        }

        return handleFiveRankUnitsOffsuit(rankUnits)
    }

    private fun handleFiveRankUnitsSuited(rankUnits: SortedSet<RankUnit>): HandRanking {
        if (rankUnits.areInConsecutiveDescOrder()) {
            handleFiveRankUnitsSuitedStraight(rankUnits)
        }

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