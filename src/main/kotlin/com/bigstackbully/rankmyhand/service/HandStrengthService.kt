package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.HandStrength
import com.bigstackbully.rankmyhand.model.combination.HandCombination
import com.bigstackbully.rankmyhand.model.enums.Ranking
import com.bigstackbully.rankmyhand.model.notation.RankNotation
import com.bigstackbully.rankmyhand.service.utils.hasFiveItems
import org.springframework.stereotype.Service

@Service
class HandStrengthService(
    val handCombinationService: HandCombinationService
) {

    fun calculateHandStrength(
        ranking: Ranking,
        hand: Hand
    ): HandStrength {
        val bestHandCombination = if (hand.hasFiveCards) {
            handCombinationService.getHandCombination(
                ranking = ranking,
                rankNotation = hand.rankNotation
            )
        } else {
            handCombinationService.findWorstPossibleHandCombination(
                signatureNotation = hand.signatureNotation
            )
        }

        if (bestHandCombination == null)
            throw IllegalStateException("Unable to calculate hand strength for hand '$hand' with ranking '${ranking.name}'. No matching hand combination found.")

        return with(bestHandCombination) {
            HandStrength(
                absolutePosition = absolutePosition,
                absoluteStrength = absoluteStrength,
                relativePosition = relativePosition,
                relativeStrength = relativeStrength
            )
        }
    }
}