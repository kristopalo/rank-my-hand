package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.combination.FLUSH_HANDS
import com.bigstackbully.rankmyhand.model.combination.FOUR_OF_A_KIND_HANDS
import com.bigstackbully.rankmyhand.model.combination.FULL_HOUSE_HANDS
import com.bigstackbully.rankmyhand.model.combination.HIGH_CARD_HANDS
import com.bigstackbully.rankmyhand.model.combination.HandCombination
import com.bigstackbully.rankmyhand.model.combination.ONE_PAIR_HANDS
import com.bigstackbully.rankmyhand.model.combination.ROYAL_FLUSH_HANDS
import com.bigstackbully.rankmyhand.model.combination.STRAIGHT_FLUSH_HANDS
import com.bigstackbully.rankmyhand.model.combination.STRAIGHT_HANDS
import com.bigstackbully.rankmyhand.model.combination.THREE_OF_A_KIND_HANDS
import com.bigstackbully.rankmyhand.model.combination.TWO_PAIR_HANDS
import com.bigstackbully.rankmyhand.model.enums.Ranking
import com.bigstackbully.rankmyhand.model.enums.Ranking.FLUSH
import com.bigstackbully.rankmyhand.model.enums.Ranking.FOUR_OF_A_KIND
import com.bigstackbully.rankmyhand.model.enums.Ranking.FULL_HOUSE
import com.bigstackbully.rankmyhand.model.enums.Ranking.HIGH_CARD
import com.bigstackbully.rankmyhand.model.enums.Ranking.ONE_PAIR
import com.bigstackbully.rankmyhand.model.enums.Ranking.ROYAL_FLUSH
import com.bigstackbully.rankmyhand.model.enums.Ranking.STRAIGHT
import com.bigstackbully.rankmyhand.model.enums.Ranking.STRAIGHT_FLUSH
import com.bigstackbully.rankmyhand.model.enums.Ranking.THREE_OF_A_KIND
import com.bigstackbully.rankmyhand.model.enums.Ranking.TWO_PAIR
import com.bigstackbully.rankmyhand.model.notation.RankNotation
import com.bigstackbully.rankmyhand.model.notation.SignatureNotation
import org.springframework.stereotype.Service

@Service
class HandCombinationService(
    private val rankingService: RankingService
) {

    companion object {
        private const val RANKS: String = "23456789TJQKA"
    }

    // TODO Kristo @ 14.10.2025 -> It's actually not very feasible to calculate the best possible hand combination this way
    // TODO ...because even if we already have a draw of 4 cards (including ACE), there are still 3 cards to be dealt.
    // TODO ...and one or more of those 3 cards could also be an ACE, so we might end up with FOAK or TOAK.

    fun findWorstPossibleHandCombination(
        signatureNotation: SignatureNotation
    ): HandCombination? {
        val allRankings = rankingService.getAllRankingsSortedByStrengthInAscOrder()

        val hRanks = signatureNotation.ranks

        val hRankCounts = RANKS.map { rank ->
            val count = hRanks.count { it.key == rank.toString() }
            count
        }

        // TODO Kristo @ 27.10.2025 -> Take into account the isSuited property of the signature notation

        for (ranking in allRankings) {
            val mapOfHandCombinations = getMapOfHandCombinations(ranking = ranking)

            val handCombinationsInAscOrder = mapOfHandCombinations.values.sortedByDescending { it.absolutePosition }

            for (hc in handCombinationsInAscOrder) {
                val hcRankCounts = hc.rankCounts

                if (hcRankCounts.zip(hRankCounts).all { (hcRankCount, hRankCount) ->  hRankCount <= hcRankCount})
                    return hc
            }
        }

        return null
    }

    fun getAllHandCombinations(): List<HandCombination> {
        val allRankings = rankingService.getAllRankings()
        return allRankings.flatMap { ranking -> getAllHandCombinationsForRanking(ranking) }
    }

    fun getAllHandCombinationsForRanking(
        ranking: Ranking
    ): List<HandCombination> = getMapOfHandCombinations(ranking = ranking).values.toList()

    // TODO Kristo @ 30.06.2025 -> We should make this function private
    fun getHandCombination(
        ranking: Ranking,
        rankNotation: RankNotation
    ): HandCombination {
        val mapOfHandCombinations = getMapOfHandCombinations(ranking)
        val rankNt = rankNotation.toString()
        return mapOfHandCombinations[rankNt]
            ?: throw IllegalArgumentException("Ranking '${ranking.name}' (key = '${ranking.key}') does not have a hand combination of '$rankNotation'.")
    }

    fun getMapOfHandCombinations(ranking: Ranking): Map<String, HandCombination> {
        return when (ranking) {
            ROYAL_FLUSH -> ROYAL_FLUSH_HANDS
            STRAIGHT_FLUSH -> STRAIGHT_FLUSH_HANDS
            FOUR_OF_A_KIND -> FOUR_OF_A_KIND_HANDS
            FULL_HOUSE -> FULL_HOUSE_HANDS
            FLUSH -> FLUSH_HANDS
            STRAIGHT -> STRAIGHT_HANDS
            THREE_OF_A_KIND -> THREE_OF_A_KIND_HANDS
            TWO_PAIR -> TWO_PAIR_HANDS
            ONE_PAIR -> ONE_PAIR_HANDS
            HIGH_CARD -> HIGH_CARD_HANDS
        }
    }
}