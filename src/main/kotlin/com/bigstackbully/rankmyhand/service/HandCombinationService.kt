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
import org.springframework.stereotype.Service

@Service
class HandCombinationService {

    fun getAllHandCombinationsForRanking(
        ranking: Ranking
    ): List<HandCombination> = getMapOfHandCombinations(ranking = ranking).values.toList()

    fun getHandCombination(
        ranking: Ranking,
        shortNotation: String
    ): HandCombination {
        val mapOfHandCombinations = getMapOfHandCombinations(ranking)
        return mapOfHandCombinations[shortNotation]
            ?: throw IllegalArgumentException("No such key found in the map of hand combinations: $shortNotation")
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
