package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.HandStrength
import com.bigstackbully.rankmyhand.model.enums.Ranking
import com.bigstackbully.rankmyhand.model.notation.RankNotation
import org.springframework.stereotype.Service

@Service
class HandStrengthService(
    val handCombinationService: HandCombinationService
) {

    fun calculateHandStrength(
        ranking: Ranking,
        rankNotation: RankNotation
    ): HandStrength {
        // TODO Kristo @ 09.10.2025 -> If shorthand is still a draw (cards.size < 5), then do the following:
        // TODO ...find the worst possible hand combination

        val handCombination = handCombinationService.getHandCombination(
            ranking = ranking,
            rankNotation = rankNotation
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