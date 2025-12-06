package com.bigstackbully.rankmyhand.testdata

import com.bigstackbully.rankmyhand.model.EvaluationContext
import com.bigstackbully.rankmyhand.model.command.EvaluationCommand
import com.bigstackbully.rankmyhand.model.enums.PlayingCard

fun composeEvaluationCommandForFullHouse() = EvaluationCommand(
    evaluationContext = EvaluationContext(
        holeCards = listOf(
            PlayingCard.ACE_OF_SPADES,
            PlayingCard.ACE_OF_HEARTS
        ),
        boardCards = listOf(
            PlayingCard.ACE_OF_DIAMONDS,
            PlayingCard.KING_OF_SPADES,
            PlayingCard.KING_OF_HEARTS
        )
    )
)