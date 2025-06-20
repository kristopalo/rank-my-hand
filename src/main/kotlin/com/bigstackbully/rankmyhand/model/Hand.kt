package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.model.enums.PlayingCard.*
import com.bigstackbully.rankmyhand.service.utils.areInConsecutiveDescOrder
import com.bigstackbully.rankmyhand.service.utils.areSuited
import com.bigstackbully.rankmyhand.service.utils.areWheelStraight
import com.bigstackbully.rankmyhand.service.utils.highestRank
import com.bigstackbully.rankmyhand.service.utils.maxUnitSize
import com.bigstackbully.rankmyhand.service.utils.serializedValue
import com.bigstackbully.rankmyhand.service.utils.shortNotation
import com.bigstackbully.rankmyhand.service.utils.standardNotation
import java.util.SortedSet
import kotlin.collections.component1
import kotlin.collections.component2

data class Hand(
    private val rankUnits: SortedSet<RankUnit> = sortedSetOf()
) {

    val cards: List<PlayingCard> = rankUnits.map { it.cards }
        .flatten()
        .sortedWith(compareByDescending<PlayingCard> { it.rank.value }.thenBy { it.suit.ordinal })

    val rankUnitCount: Int = rankUnits.size
    val standardNotation: String = rankUnits.standardNotation()
    val shortNotation: String = rankUnits.shortNotation()
    val serializedValue: String = rankUnits.serializedValue()
    val highestRank: CardRank? = rankUnits.highestRank()
    val maxUnitSize: Int? = rankUnits.maxUnitSize()
    val isSuited: Boolean = rankUnits.areSuited()
    val areCardsInConsecutiveDescOrder: Boolean = rankUnits.areInConsecutiveDescOrder()

    companion object {
        fun of(cards: List<PlayingCard>): Hand {
            require(cards.size == 5) {
                "A hand can only contain exactly 5 playing cards."
            }

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