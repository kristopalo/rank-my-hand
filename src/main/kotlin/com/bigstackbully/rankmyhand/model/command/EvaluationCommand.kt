package com.bigstackbully.rankmyhand.model.command

import com.bigstackbully.rankmyhand.model.HandContext
import com.bigstackbully.rankmyhand.model.enums.PlayingCard

data class EvaluationCommand(
    val handContext: HandContext
) {

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