package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.characteristic.HasRanks
import com.bigstackbully.rankmyhand.model.characteristic.SuitAware
import com.bigstackbully.rankmyhand.model.enums.Rank
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.model.enums.PlayingCard.*
import com.bigstackbully.rankmyhand.model.notation.RankNotation
import com.bigstackbully.rankmyhand.model.notation.SignatureNotation
import com.bigstackbully.rankmyhand.model.notation.StandardNotation
import com.bigstackbully.rankmyhand.service.utils.areStraight
import com.bigstackbully.rankmyhand.service.utils.areSuited
import com.bigstackbully.rankmyhand.service.utils.areWheelStraight
import com.bigstackbully.rankmyhand.service.utils.ranks
import com.bigstackbully.rankmyhand.service.utils.highestRank
import com.bigstackbully.rankmyhand.service.utils.serializedValue
import com.bigstackbully.rankmyhand.service.utils.toRankNotation
import com.bigstackbully.rankmyhand.service.utils.toSignatureNotation
import com.bigstackbully.rankmyhand.service.utils.toStandardNotation
import java.util.SortedSet

data class Hand(
    private val rankUnits: SortedSet<RankUnit> = sortedSetOf()
) : HasRanks, SuitAware {

    val cards: List<PlayingCard> = rankUnits.map { it.cards }
        .flatten()
        .sortedWith(compareByDescending<PlayingCard> { it.rank.value }.thenBy { it.suit.ordinal })

    override val ranks = rankUnits.ranks()
    override val isSuited: Boolean = rankUnits.areSuited()

    val isEmpty: Boolean = rankUnits.isEmpty()
    val highestRank: Rank? = rankUnits.highestRank()
    val areCardsInConsecutiveDescOrder: Boolean = cards.areStraight()

    val hasFourOfAKind: Boolean = rankUnits.any { it.isFourOfAKind }
    val hasThreeOfAKind: Boolean = rankUnits.any { it.isThreeOfAKind }
    val hasTwoPairs: Boolean = rankUnits.count { it.isPair } == 2
    val hasPair: Boolean = rankUnits.any { it.isPair }

    val rankNotation: RankNotation = rankUnits.toRankNotation()
    val signatureNotation: SignatureNotation = rankUnits.toSignatureNotation()
    val standardNotation: StandardNotation = rankUnits.toStandardNotation()
    val serializedValue: String = rankUnits.serializedValue()

    override fun toString() = standardNotation.toString()

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