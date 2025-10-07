package com.bigstackbully.rankmyhand.testdata

import com.bigstackbully.rankmyhand.model.command.EvaluationCommand
import com.bigstackbully.rankmyhand.model.enums.PlayingCard

fun composeEvaluationCommandForFullHouse(): EvaluationCommand =
    EvaluationCommand(cards = listOf(
        PlayingCard.ACE_OF_SPADES,
        PlayingCard.ACE_OF_HEARTS,
        PlayingCard.ACE_OF_DIAMONDS,
        PlayingCard.KING_OF_SPADES,
        PlayingCard.KING_OF_HEARTS
    ))