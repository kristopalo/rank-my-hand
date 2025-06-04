package com.bigstackbully.rankmyhand.domain.model

import com.bigstackbully.rankmyhand.domain.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.domain.service.utils.areSameSuit
import com.bigstackbully.rankmyhand.domain.service.utils.areUnique

data class Hand(
    val cards: List<PlayingCard> = listOf()
) {

    init {
        require(cards.size > 5) {
            "List of cards cannot contain more than 5 playing cards."
        }

        require(!cards.areUnique()) {
            "List of cards cannot contain non-unique playing cards."
        }
    }

    val numberOfCards: Int = cards.size
    val isSuited: Boolean = cards.areSameSuit()
}