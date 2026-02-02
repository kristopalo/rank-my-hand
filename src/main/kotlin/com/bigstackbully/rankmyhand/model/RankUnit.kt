package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.enums.Card
import com.bigstackbully.rankmyhand.model.enums.Rank
import com.bigstackbully.rankmyhand.model.enums.Suit
import com.bigstackbully.rankmyhand.utils.SINGLE_SPACE

data class RankUnit(
    val cards: Set<Card> = setOf()
) : Comparable<RankUnit> {

    companion object {
        fun of(cards: List<Card>): RankUnit {
            val setOfCards = cards.toSet()
            return RankUnit(setOfCards)
        }
    }

    init {
        require(cards.map { it.rank }.distinct().count() == 1) {
            "All cards in a rank unit must have the same card rank."
        }
    }

    val rank: Rank = cards.first().rank
    val suits: List<Suit> = cards.map { card -> card.suit }
    val numberOfCards: Int = cards.count()
    val totalValue: Int = cards.sumOf { card -> card.rank.value }

    val isFourOfAKind: Boolean = numberOfCards == 4
    val isThreeOfAKind: Boolean = numberOfCards == 3
    val isPair: Boolean = numberOfCards == 2

    val standardNotation: String = cards
        .sortedBy { it.suit.ordinal }
        .joinToString(separator = SINGLE_SPACE) { it.standardNotation }

    override fun compareTo(other: RankUnit): Int {
        return compareByDescending<RankUnit> { it.numberOfCards }
            .thenByDescending { it.rank.value }
            .compare(this, other)
    }
}