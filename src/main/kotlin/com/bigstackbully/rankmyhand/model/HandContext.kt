package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.characteristic.HasCards
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.model.enums.Rank
import com.bigstackbully.rankmyhand.model.enums.Street
import kotlin.collections.filterNot

data class HandContext(
    val holeCards: List<PlayingCard>,
    val boardCards: List<PlayingCard>
) : HasCards {

    override val cards: List<PlayingCard> = holeCards + boardCards

    val numberOfCardsDrawn: Int = cards.size
    val numberOfCardsLeftToDraw = 7 - numberOfCardsDrawn

    val numberOfCardsRemainingInDeck = PlayingCard.entries.filterNot { it.rank == Rank.LOW_ACE }.size - numberOfCardsDrawn

    val street: Street = when (numberOfCardsDrawn) {
        in 0..2 -> Street.PRE_FLOP
        in 3..5 -> Street.FLOP
        6 -> Street.TURN
        7 -> Street.RIVER
        else -> error("Unable to determine street: $numberOfCardsDrawn")
    }
}
