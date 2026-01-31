package com.bigstackbully.rankmyhand.model.enums

import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.enums.interfaces.KeyedEnum
import com.bigstackbully.rankmyhand.model.conditions.hasAtLeastFourCards
import com.bigstackbully.rankmyhand.model.conditions.hasAtLeastOneCard
import com.bigstackbully.rankmyhand.model.conditions.hasAtLeastThreeCards
import com.bigstackbully.rankmyhand.model.conditions.hasAtLeastTwoCards
import com.bigstackbully.rankmyhand.model.conditions.hasFiveCards
import com.bigstackbully.rankmyhand.model.conditions.hasFourOfAKind
import com.bigstackbully.rankmyhand.model.conditions.hasPair
import com.bigstackbully.rankmyhand.model.conditions.hasThreeOfAKind
import com.bigstackbully.rankmyhand.model.conditions.hasTwoPairs
import com.bigstackbully.rankmyhand.model.conditions.isAceHigh
import com.bigstackbully.rankmyhand.model.conditions.isStraight
import com.bigstackbully.rankmyhand.model.conditions.isSuited
import com.bigstackbully.rankmyhand.utils.EMPTY_STRING
import com.bigstackbully.rankmyhand.utils.SINGLE_SPACE
import com.bigstackbully.rankmyhand.utils.UNDERSCORE

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
        .split(UNDERSCORE).joinToString(separator = EMPTY_STRING) { it.first().uppercase() }

    val displayName: String = name
        .split(UNDERSCORE)
        .joinToString(separator = SINGLE_SPACE) { "${it.first().uppercase()}${it.substring(1).lowercase()}" }
}