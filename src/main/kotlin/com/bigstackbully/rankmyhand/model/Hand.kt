package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.service.utils.areSameSuit

data class Hand(
    val cards: Set<PlayingCard> = setOf()
) {
    init {
        require(cards.size == 5) {
            "A hand can only contain exactly 5 playing cards."
        }
    }

    val numberOfCards: Int = cards.size
    val isSuited: Boolean = cards.areSameSuit()
}