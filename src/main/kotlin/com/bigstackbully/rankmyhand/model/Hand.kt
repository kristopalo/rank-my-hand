package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.characteristic.HasCards
import com.bigstackbully.rankmyhand.model.characteristic.HasRanks
import com.bigstackbully.rankmyhand.model.characteristic.SuitAware
import com.bigstackbully.rankmyhand.model.enums.Card
import com.bigstackbully.rankmyhand.model.enums.Card.*
import com.bigstackbully.rankmyhand.model.enums.Rank
import com.bigstackbully.rankmyhand.service.utils.*
import java.util.*

data class Hand(
    private val rankUnits: SortedSet<RankUnit> = sortedSetOf()
) : HasRanks, SuitAware, HasCards {

    override val cards: List<Card> = rankUnits
        .flatMap { it.cards }
        .sortedWith(compareByDescending<Card> { it.rank.value }.thenBy { it.suit.ordinal })

    override val ranks = rankUnits.ranks()
    override val isSuited: Boolean = rankUnits.areSuited()

    val isEmpty: Boolean = rankUnits.isEmpty()
    val highestRank: Rank? = rankUnits.highestRank()
    val areCardsInConsecutiveDescOrder: Boolean = cards.areStraight()

    val hasFourOfAKind: Boolean = rankUnits.any { it.isFourOfAKind }
    val hasThreeOfAKind: Boolean = rankUnits.any { it.isThreeOfAKind }
    val hasTwoPairs: Boolean = rankUnits.count { it.isPair } == 2
    val hasPair: Boolean = rankUnits.any { it.isPair }
    val hasFiveCards = cards.count() == 5

    val rankNotation: String = rankUnits.toRankNotation()
    val standardNotation: String = rankUnits.toStandardNotation()
    val serializedValue: String = rankUnits.serializedValue()

    override fun toString() = standardNotation

    companion object {
        fun of(cards: List<Card>): Hand {
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