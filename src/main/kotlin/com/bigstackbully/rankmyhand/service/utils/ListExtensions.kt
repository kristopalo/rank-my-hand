package com.bigstackbully.rankmyhand.service.utils

import com.bigstackbully.rankmyhand.model.enums.PlayingCard

fun Collection<PlayingCard>.areSameSuit(): Boolean {
    require(isNotEmpty()) {
        throw IllegalStateException("An empty list cannot be of same suit.")
    }

    val firstCard = this.first()
    val suitOfFirstCard = firstCard.suit

    return this.all { card -> card.suit == suitOfFirstCard }
}

fun List<PlayingCard>.areUnique(): Boolean {
    return this.size == this.distinct().size
}