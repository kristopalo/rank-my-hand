package com.bigstackbully.rankmyhand.testdata

import com.bigstackbully.rankmyhand.model.EvaluationContext
import com.bigstackbully.rankmyhand.model.command.EvaluationCommand
import com.bigstackbully.rankmyhand.model.enums.Card

fun composeEvaluationCommandForFullHouse() = EvaluationCommand(
    evaluationContext = EvaluationContext(
        holeCards = listOf(
            Card.ACE_OF_SPADES,
            Card.ACE_OF_HEARTS
        ),
        boardCards = listOf(
            Card.ACE_OF_DIAMONDS,
            Card.KING_OF_SPADES,
            Card.KING_OF_HEARTS
        )
    )
)