package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.HandStrength
import com.bigstackbully.rankmyhand.model.enums.Ranking
import org.springframework.stereotype.Service

@Service
class HandStrengthService(
    val handCombinationService: HandCombinationService
) {

    fun calculateHandStrength(
        ranking: Ranking,
        shorthandNotation: String
    ): HandStrength {
        val handCombination = handCombinationService.getHandCombination(
            ranking = ranking,
            shorthandNotation = shorthandNotation
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