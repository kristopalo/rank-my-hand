package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.model.enums.Suit

data class RankUnit(
    val cards: Set<PlayingCard> = setOf()
) : Comparable<RankUnit> {

    companion object {
        fun of(cards: List<PlayingCard>): RankUnit {
            val setOfCards = cards.toSet()
            return RankUnit(setOfCards)
        }
    }

    init {
        require(cards.map { it.rank }.distinct().size == 1) {
            "All playing cards in a rank unit must have the same card rank."
        }
    }

    val rank: CardRank = cards.first().rank
    val suits: List<Suit> = cards.map { card -> card.suit }
    val numberOfCards: Int = cards.size
    val totalValue: Int = cards.sumOf { card -> card.rank.value }
    val standardNotation: String = cards.joinToString(separator = " ") { card -> card.standardNotation }
    val shortNotation: String = cards.joinToString(separator = "") { card -> card.rank.shortNotation }

    override fun compareTo(other: RankUnit): Int {
        return compareByDescending<RankUnit> { it.numberOfCards }
            .thenByDescending { it.rank.value }
            .compare(this, other)
    }
}