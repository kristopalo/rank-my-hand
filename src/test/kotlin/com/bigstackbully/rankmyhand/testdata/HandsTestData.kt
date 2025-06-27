package com.bigstackbully.rankmyhand.testdata

import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.enums.PlayingCard.*

fun composeHandOfFullHouse() = Hand.of(
    cards = listOf(
        ACE_OF_SPADES,
        ACE_OF_HEARTS,
        ACE_OF_DIAMONDS,
        KING_OF_SPADES,
        KING_OF_HEARTS,
    )
)