package com.bigstackbully.rankmyhand.domain.service.utils

import com.bigstackbully.rankmyhand.domain.model.enums.PlayingCard

fun List<PlayingCard>.areSameSuit(): Boolean {
    if (this.isEmpty())
        throw IllegalStateException("An empty list cannot be of same suit.")

    val firstCard = this.first()
    val suitOfFirstCard = firstCard.suit

    return this.all { card -> card.suit == suitOfFirstCard }
}

fun List<PlayingCard>.areUnique(): Boolean {
    return this.size == this.distinct().size
}