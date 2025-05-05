package com.bigstackbully.rankmyhand.domain.model

import com.bigstackbully.rankmyhand.domain.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.domain.model.interfaces.SetOfCards
import com.bigstackbully.rankmyhand.domain.service.utils.areUnique

@Suppress("DataClassPrivateConstructor")
data class SelectionSet private constructor(
    private val cards: List<PlayingCard> = listOf()
) : SetOfCards(cards) {

    companion object {
        fun of(cards: List<PlayingCard>): SelectionSet {
            if (cards.isEmpty())
                throw IllegalArgumentException("List of cards has to contain at least 1 playing card.")

            if (cards.size > 7)
                throw IllegalArgumentException("List of cards cannot contain more than 7 playing cards.")

            if (!cards.areUnique())
                throw IllegalArgumentException("List of cards cannot contain non-unique playing cards.")

            return SelectionSet(cards)
        }
    }
}