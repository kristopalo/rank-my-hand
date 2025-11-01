package com.bigstackbully.rankmyhand.model.command

import com.bigstackbully.rankmyhand.model.HandContext
import com.bigstackbully.rankmyhand.model.enums.PlayingCard

data class EvaluationCommand(
    // TODO Kristo @ 02.11.2025 -> Start distinguishing between hole cards and board cards.
    val handContext: HandContext
) {

    val cards: List<PlayingCard> = handContext.cards

    companion object {

        fun of(
            holeCards: List<PlayingCard>,
            boardCards: List<PlayingCard>
        ) = EvaluationCommand(
            handContext = HandContext(
                holeCards = holeCards,
                boardCards = boardCards
            )
        )

    }
}