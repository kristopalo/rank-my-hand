package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.model.enums.Suit

data class RankUnit(
    val cards: Set<PlayingCard> = sortedSetOf()
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
    val abbreviation: String = cards.joinToString(separator = "") { it.rank.shortNotation }
    val ranksInStandardNotation = cards.joinToString(separator = "") { card -> card.rank.shortNotation }

    override fun compareTo(other: RankUnit): Int {
        return compareByDescending<RankUnit> { it.numberOfCards }
            .thenByDescending { it.rank.value }
            .compare(this, other)
    }
}

val rankUnitComparator = compareByDescending<RankUnit> { it.numberOfCards }
    .thenByDescending { it.rank }