package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.HandStrength
import com.bigstackbully.rankmyhand.model.combination.TWO_PAIR_HANDS
import com.bigstackbully.rankmyhand.model.combination.FLUSH_HANDS
import com.bigstackbully.rankmyhand.model.combination.FOUR_OF_A_KIND_HANDS
import com.bigstackbully.rankmyhand.model.combination.FULL_HOUSE_HANDS
import com.bigstackbully.rankmyhand.model.combination.HIGH_CARD_HANDS
import com.bigstackbully.rankmyhand.model.combination.ONE_PAIR_HANDS
import com.bigstackbully.rankmyhand.model.combination.ROYAL_FLUSH_HANDS
import com.bigstackbully.rankmyhand.model.combination.STRAIGHT_FLUSH_HANDS
import com.bigstackbully.rankmyhand.model.combination.STRAIGHT_HANDS
import com.bigstackbully.rankmyhand.model.combination.THREE_OF_A_KIND_HANDS
import com.bigstackbully.rankmyhand.model.enums.HandRanking
import com.bigstackbully.rankmyhand.model.enums.HandRanking.*
import org.springframework.stereotype.Service

@Service
class HandStrengthService {

    fun calculateHandStrength(
        handRanking: HandRanking,
        shortNotation: String
    ): HandStrength {
        val mapOfHandCombinations = when (handRanking) {
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

        val handCombination = mapOfHandCombinations[shortNotation]
            ?: throw IllegalArgumentException("No such key found in the map of hand combinations: $shortNotation")

        return HandStrength(
            absolutePosition = handCombination.absolutePosition,
            absoluteStrength = handCombination.absoluteStrength,
            relativePosition = handCombination.relativePosition,
            relativeStrength = handCombination.relativeStrength
        )
    }
}