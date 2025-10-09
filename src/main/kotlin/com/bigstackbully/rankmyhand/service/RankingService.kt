package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.enums.Ranking
import com.bigstackbully.rankmyhand.model.exception.EnumNotFoundException
import org.springframework.stereotype.Service

@Service
class RankingService {

    fun getAllRankings() = Ranking.entries

    fun getAllRankingsSortedByStrengthInDescOrder() = getAllRankings().sortedByDescending { it.strength }

    fun getRankingByKey(key: String): Ranking? = getAllRankings().firstOrNull { it.key.equals(key, ignoreCase = true) }

    fun getRankingByName(name: String): Ranking? = getAllRankings().firstOrNull {
        it.name.equals(name, ignoreCase = true)
    }

    fun getRankingByKeyOrName(identifier: String): Ranking =
        getRankingByKey(identifier) ?: getRankingByName(identifier)
        ?: throw EnumNotFoundException(identifier = identifier, enumClass = Ranking::class)

    fun evaluateRanking(hand: Hand): Ranking {
        if (hand.isEmpty)
            throw IllegalArgumentException("Cannot evaluate the ranking of an empty hand.")

        return getAllRankingsSortedByStrengthInDescOrder().firstOrNull { ranking ->
            ranking.conditions.all { condition -> condition(hand) }
        } ?: throw IllegalStateException("Unable to evaluate ranking for the following hand: $hand")
    }
}