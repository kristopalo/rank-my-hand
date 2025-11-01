package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.enums.PlayingCard

data class HandContext(
    val holeCards: List<PlayingCard>,
    val boardCards: List<PlayingCard>
) {

    val cards: List<PlayingCard> = holeCards + boardCards

}
