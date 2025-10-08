package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.model.enums.PlayingCard.*
import com.bigstackbully.rankmyhand.service.utils.areStraight
import com.bigstackbully.rankmyhand.service.utils.areSuited
import com.bigstackbully.rankmyhand.service.utils.areWheelStraight
import com.bigstackbully.rankmyhand.service.utils.highestRank
import com.bigstackbully.rankmyhand.service.utils.serializedValue
import com.bigstackbully.rankmyhand.service.utils.shortNotation
import com.bigstackbully.rankmyhand.service.utils.standardNotation
import java.util.SortedSet

data class Hand(
    private val rankUnits: SortedSet<RankUnit> = sortedSetOf()
) {

    val cards: List<PlayingCard> = rankUnits.map { it.cards }
        .flatten()
        .sortedWith(compareByDescending<PlayingCard> { it.rank.value }.thenBy { it.suit.ordinal })

    val isEmpty: Boolean = rankUnits.isEmpty()
    val highestRank: CardRank? = rankUnits.highestRank()
    val isSuited: Boolean = rankUnits.areSuited()
    val areCardsInConsecutiveDescOrder: Boolean = cards.areStraight()

    val hasFourOfAKind: Boolean = rankUnits.any { it.isFourOfAKind }
    val hasThreeOfAKind: Boolean = rankUnits.any { it.isThreeOfAKind }
    val hasTwoPairs: Boolean = rankUnits.count { it.isPair } == 2
    val hasPair: Boolean = rankUnits.any { it.isPair }

    val standardNotation: String = rankUnits.standardNotation()
    val shortNotation: String = rankUnits.shortNotation()
    val serializedValue: String = rankUnits.serializedValue()

    override fun toString(): String {
        return standardNotation
    }

    companion object {

        fun of(cards: List<PlayingCard>): Hand {
            val normalizedCards = if (cards.areWheelStraight()) {
                cards.map { card ->
                    when (card) {
                        ACE_OF_SPADES -> LOW_ACE_OF_SPADES
                        ACE_OF_HEARTS -> LOW_ACE_OF_HEARTS
                        ACE_OF_DIAMONDS -> LOW_ACE_OF_DIAMONDS
                        ACE_OF_CLUBS -> LOW_ACE_OF_CLUBS
                        else -> card
                    }
                }
            } else {
                cards
            }

            val rankUnits = normalizedCards
                .groupBy { it.rank }
                .map { (_, cards) -> RankUnit.of(cards) }
                .toSortedSet()

            return Hand(rankUnits = rankUnits)
        }
    }
}