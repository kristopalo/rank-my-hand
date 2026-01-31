package com.bigstackbully.rankmyhand.model.command

import com.bigstackbully.rankmyhand.model.EvaluationContext
import com.bigstackbully.rankmyhand.model.enums.Card

data class EvaluationCommand(
    val evaluationContext: EvaluationContext
) {
    companion object {
        fun of(
            holeCards: List<Card>,
            boardCards: List<Card>
        ) = EvaluationCommand(
            evaluationContext = EvaluationContext(
                holeCards = holeCards,
                boardCards = boardCards
            )
        )
    }
}