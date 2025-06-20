package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.model.enums.PlayingCard.*
import com.bigstackbully.rankmyhand.service.utils.areSameSuit
import com.bigstackbully.rankmyhand.service.utils.areWheelStraight

data class Hand(
    val cards: Set<PlayingCard> = setOf()
) {

    companion object {
        fun of(cards: Set<PlayingCard>): Hand {
            val normalizedCards = if (cards.areWheelStraight()) {
                cards.map { card ->
                    when (card) {
                        ACE_OF_SPADES -> LOW_ACE_OF_SPADES
                        ACE_OF_HEARTS -> LOW_ACE_OF_HEARTS
                        ACE_OF_DIAMONDS -> LOW_ACE_OF_DIAMONDS
                        ACE_OF_CLUBS -> LOW_ACE_OF_CLUBS
                        else -> card
                    }
                }.toSet()
            } else {
                cards
            }

            return Hand(cards = normalizedCards)
        }
    }

    init {
        require(cards.size == 5) {
            "A hand can only contain exactly 5 playing cards."
        }
    }

    val numberOfCards: Int = cards.size
    val isSuited: Boolean = cards.areSameSuit()
}