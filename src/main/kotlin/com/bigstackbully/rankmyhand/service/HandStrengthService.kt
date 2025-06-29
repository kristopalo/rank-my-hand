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
import com.bigstackbully.rankmyhand.model.enums.Ranking
import com.bigstackbully.rankmyhand.model.enums.Ranking.*
import org.springframework.stereotype.Service

@Service
class HandStrengthService(
    val handCombinationService: HandCombinationService
) {

    fun calculateHandStrength(
        ranking: Ranking,
        shortNotation: String
    ): HandStrength {
        val handCombination = handCombinationService.getHandCombination(
            ranking = ranking,
            shortNotation = shortNotation
        )

        return with(handCombination) {
            HandStrength(
                absolutePosition = absolutePosition,
                absoluteStrength = absoluteStrength,
                relativePosition = relativePosition,
                relativeStrength = relativeStrength
            )
        }
    }
}