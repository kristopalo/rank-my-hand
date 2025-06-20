package com.bigstackbully.rankmyhand.service.utils

import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.PlayingCard

fun Collection<PlayingCard>.areSameSuit(): Boolean {
    require(isNotEmpty()) {
        throw IllegalStateException("An empty list cannot be of same suit.")
    }

    val firstCard = this.first()
    val suitOfFirstCard = firstCard.suit

    return all { card -> card.suit == suitOfFirstCard }
}

fun List<PlayingCard>.areUnique(): Boolean = size == distinct().size

fun Collection<PlayingCard>.areWheelStraight(): Boolean {
    val ranks = map { it.rank }
    return size == 5 && ranks.containsAll(listOf(
        CardRank.FIVE,
        CardRank.FOUR,
        CardRank.THREE,
        CardRank.TWO,
        CardRank.ACE
    ))
}