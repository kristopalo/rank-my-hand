package com.bigstackbully.rankmyhand.domain.model

import com.bigstackbully.rankmyhand.domain.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.domain.model.interfaces.SetOfCards
import com.bigstackbully.rankmyhand.domain.service.utils.areUnique

data class Hand(
    private val cards: List<PlayingCard> = listOf()
) : SetOfCards(cards = cards) {

    init {
        if (cards.isEmpty())
            throw IllegalArgumentException("List of cards has to contain at least 1 playing card.")

        if (cards.size > 5)
            throw IllegalArgumentException("List of cards cannot contain more than 5 playing cards.")

        if (!cards.areUnique())
            throw IllegalArgumentException("List of cards cannot contain non-unique playing cards.")
    }
}