package com.bigstackbully.rankmyhand.domain.model

import com.bigstackbully.rankmyhand.domain.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.domain.service.utils.areSameSuit
import com.bigstackbully.rankmyhand.domain.service.utils.areUnique

data class Hand(
    val cards: List<PlayingCard> = listOf()
) {

    init {
        if (cards.size > 5)
            throw IllegalArgumentException("List of cards cannot contain more than 5 playing cards.")

        if (!cards.areUnique())
            throw IllegalArgumentException("List of cards cannot contain non-unique playing cards.")
    }

    val numberOfCards: Int = cards.size
    val isSuited: Boolean = cards.areSameSuit()
}