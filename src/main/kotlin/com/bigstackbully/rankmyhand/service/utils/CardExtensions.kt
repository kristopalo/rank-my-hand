package com.bigstackbully.rankmyhand.service.utils

import com.bigstackbully.rankmyhand.model.enums.Rank
import com.bigstackbully.rankmyhand.model.enums.PlayingCard

fun List<PlayingCard>.areUnique(): Boolean = size == distinct().size

fun List<PlayingCard>.areStraight(): Boolean {
    val sortedRanks = map { it.rank.value }.sortedDescending()
    return sortedRanks.zipWithNext().all { (a, b) -> a - 1 == b }
}

fun Collection<PlayingCard>.areWheelStraight() = map { it.rank }.areWheelStraight()

fun List<PlayingCard>.areSuited(): Boolean = map { it.suit }.distinct().size == 1