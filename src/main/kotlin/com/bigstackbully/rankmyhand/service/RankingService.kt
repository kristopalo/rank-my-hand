package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.Ranking
import org.springframework.stereotype.Service

@Service
class RankingService {

    fun getAllRankings(): List<Ranking> {
        return Ranking.entries
    }

    fun determineRanking(hand: Hand): Ranking {
        return when (hand.rankUnitCount) {
            2 -> evaluateHandWithTwoRankUnits(hand)
            3 -> evaluateHandWithThreeRankUnits(hand)
            4 -> evaluateHandWithFourRankUnits()
            5 -> evaluateHandWithFiveRankUnits(hand)
            else -> error("The number of rank units has to be between 2 and 5, inclusive.")
        }
    }

    private fun evaluateHandWithTwoRankUnits(hand: Hand): Ranking {
        if (hand.maxUnitSize == 4)
            return Ranking.FOUR_OF_A_KIND

        return Ranking.FULL_HOUSE
    }

    private fun evaluateHandWithThreeRankUnits(hand: Hand): Ranking {
        if (hand.maxUnitSize == 3)
            return Ranking.THREE_OF_A_KIND

        return Ranking.TWO_PAIR
    }

    private fun evaluateHandWithFourRankUnits(): Ranking {
        return Ranking.ONE_PAIR
    }

    private fun evaluateHandWithFiveRankUnits(hand: Hand): Ranking {
        if (hand.isSuited)
            return handleFiveRankUnitsSuited(hand)

        return handleFiveRankUnitsOffsuit(hand)
    }

    private fun handleFiveRankUnitsSuited(hand: Hand): Ranking {
        if (hand.areCardsInConsecutiveDescOrder)
            return handleFiveRankUnitsSuitedStraight(hand)

        return Ranking.FLUSH
    }

    private fun handleFiveRankUnitsSuitedStraight(hand: Hand): Ranking {
        if (hand.highestRank == CardRank.ACE)
            return Ranking.ROYAL_FLUSH

        return Ranking.STRAIGHT_FLUSH
    }

    private fun handleFiveRankUnitsOffsuit(hand: Hand): Ranking {
        if (hand.areCardsInConsecutiveDescOrder)
            return Ranking.STRAIGHT

        return Ranking.HIGH_CARD
    }
}