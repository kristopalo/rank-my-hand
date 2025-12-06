package com.bigstackbully.rankmyhand.model.command

import com.bigstackbully.rankmyhand.model.EvaluationContext
import com.bigstackbully.rankmyhand.model.enums.PlayingCard

data class EvaluationCommand(
    val evaluationContext: EvaluationContext
) {

    companion object {
        fun of(
            holeCards: List<PlayingCard>,
            boardCards: List<PlayingCard>
        ) = EvaluationCommand(
            evaluationContext = EvaluationContext(
                holeCards = holeCards,
                boardCards = boardCards
            )
        )
    }
}