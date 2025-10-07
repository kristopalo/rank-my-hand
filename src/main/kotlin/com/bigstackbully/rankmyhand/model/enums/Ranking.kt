package com.bigstackbully.rankmyhand.model.enums

import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.enums.interfaces.KeyedEnum

val hasFiveCards: (Hand) -> Boolean = { hand -> hand.cards.size == 5 }
val hasAtLeastFourCards: (Hand) -> Boolean = { hand -> hand.cards.size >= 4 }
val hasAtLeastThreeCards: (Hand) -> Boolean = { hand -> hand.cards.size >= 3 }
val hasAtLeastTwoCards: (Hand) -> Boolean = { hand -> hand.cards.size >= 2 }
val hasAtLeastOneCard: (Hand) -> Boolean = { hand -> hand.cards.isNotEmpty() }

val isSuited = { hand: Hand -> hand.isSuited }
val isStraight = { hand: Hand -> hand.areCardsInConsecutiveDescOrder }

val isAceHigh: (Hand) -> Boolean = { hand -> hand.highestRank == CardRank.ACE }
val hasFourOfAKind: (Hand) -> Boolean = { hand -> hand.containsFourOfAKind }
val hasThreeOfAKind: (Hand) -> Boolean = { hand -> hand.containsThreeOfAKind }
val hasTwoPairs: (Hand) -> Boolean = { hand -> hand.containsTwoPairs }
val hasPair: (Hand) -> Boolean = { hand -> hand.containsPair }

enum class Ranking(
    val strength: Int,
    val conditions: List<(Hand) -> Boolean> = emptyList()
) : KeyedEnum {
    ROYAL_FLUSH(
        strength = 10,
        conditions = listOf(hasFiveCards, isSuited, isStraight, isAceHigh)
    ),
    STRAIGHT_FLUSH(
        strength = 9,
        conditions = listOf(hasFiveCards, isSuited, isStraight)
    ),
    FOUR_OF_A_KIND(
        strength = 8,
        conditions = listOf(hasAtLeastFourCards, hasFourOfAKind)
    ),
    FULL_HOUSE(
        strength = 7,
        conditions = listOf(hasThreeOfAKind, hasPair),
    ),
    FLUSH(
        strength = 6,
        conditions = listOf(hasFiveCards, isSuited)
    ),
    STRAIGHT(
        strength = 5,
        conditions = listOf(hasFiveCards, isStraight)
    ),
    THREE_OF_A_KIND(
        strength = 4,
        conditions = listOf(hasAtLeastThreeCards, hasThreeOfAKind)
    ),
    TWO_PAIR(
        strength = 3,
        conditions = listOf(hasAtLeastFourCards, hasTwoPairs)
    ),
    ONE_PAIR(
        strength = 2,
        conditions = listOf(hasAtLeastTwoCards, hasPair)
    ),
    HIGH_CARD(
        strength = 1,
        conditions = listOf(hasAtLeastOneCard)
    );

    override val key: String = name
        .split("_").joinToString(separator = "") { it.first().uppercase() }

    val displayName: String = name
        .split("_").joinToString(separator = " ") { "${it.first().uppercase()}${it.substring(1).lowercase()}" }
}