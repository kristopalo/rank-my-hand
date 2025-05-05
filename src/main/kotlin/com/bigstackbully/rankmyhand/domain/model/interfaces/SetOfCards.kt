package com.bigstackbully.rankmyhand.domain.model.interfaces

import com.bigstackbully.rankmyhand.domain.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.domain.service.utils.areSameSuit

abstract class SetOfCards(
    private val cards: List<PlayingCard>,
    val numberOfCards: Int = cards.size,
    val isSuited: Boolean = cards.areSameSuit()
) {

    fun getCards(): List<PlayingCard> {
        return cards
    }
}