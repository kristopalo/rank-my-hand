package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.combination.*
import com.bigstackbully.rankmyhand.model.enums.Rank
import com.bigstackbully.rankmyhand.model.enums.Ranking
import com.bigstackbully.rankmyhand.model.enums.Ranking.*
import org.springframework.stereotype.Service

@Service
class HandCombinationService(
    private val rankingService: RankingService
) {

    fun getHandCombination(
        ranking: Ranking,
        rankNotation: String
    ): HandCombination {
        val mapOfHandCombinations = getMapOfHandCombinations(ranking)
        return mapOfHandCombinations[rankNotation]
            ?: throw IllegalArgumentException("Ranking '${ranking.name}' (key = '${ranking.key}') does not have a hand combination of '$rankNotation'.")
    }

    fun findWorstPossibleHandCombination(
        ranking: Ranking,
        ranks: List<Rank>
    ): HandCombination? {
        val hRankCounts = RANKS.map { rank -> ranks.count { it.key == rank.toString() } }

        val mapOfHandCombinations = getMapOfHandCombinations(ranking = ranking)
        val handCombinationsInAscOrder = mapOfHandCombinations.values.sortedByDescending { it.absolutePosition }

        for (hc in handCombinationsInAscOrder) {
            if (hc.rankCounts.zip(hRankCounts).all { (hcRankCount, hRankCount) -> hRankCount <= hcRankCount })
                return hc
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

    companion object {
        private const val RANKS: String = "23456789TJQKA"
    }
}