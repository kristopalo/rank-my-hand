package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.model.enums.Suit

data class CardGroup(
    val cards: Set<PlayingCard> = sortedSetOf()
) : Comparable<CardGroup> {

    companion object {
        fun of(cards: List<PlayingCard>): CardGroup {
            val setOfCards = cards.toSet()
            return CardGroup(setOfCards)
        }
    }

    init {
        require(cards.map { it.rank }.distinct().size == 1) {
            "All cards in a card group must have the same rank."
        }
    }

    val rank: CardRank = cards.first().rank
    val suits: List<Suit> = cards.map { card -> card.suit }
    val numberOfCards: Int = cards.size
    val totalValue: Int = cards.sumOf { card -> card.rank.value }

    val isSingleSuit = suits.distinct().size == 1

    override fun compareTo(other: CardGroup): Int {
        return compareByDescending<CardGroup> { it.numberOfCards }
            .thenByDescending { it.rank.value }
            .compare(this, other)
    }

}

val cardGroupComparator = compareByDescending<CardGroup> { it.numberOfCards }
    .thenByDescending { it.rank }