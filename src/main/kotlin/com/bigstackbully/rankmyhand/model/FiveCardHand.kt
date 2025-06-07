package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.service.utils.areSameSuit
import com.bigstackbully.rankmyhand.service.utils.areUnique

// TODO Kristo @ 07.06.2025 -> Maybe rename this data class back to just "Hand", because there might be less than 5 cards here...?
data class FiveCardHand(
    val cards: List<PlayingCard> = listOf()
) {

    init {
        require(cards.size == 5) {
            "List of cards cannot contain more than 5 playing cards."
        }

        require(cards.areUnique()) {
            "List of cards cannot contain non-unique playing cards."
        }
    }

    val numberOfCards: Int = cards.size
    val isSuited: Boolean = cards.areSameSuit()
}