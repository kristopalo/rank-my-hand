package com.bigstackbully.rankmyhand.service.utils

import com.bigstackbully.rankmyhand.model.enums.Card

fun List<Card>.areUnique(): Boolean = count() == distinct().count()

fun List<Card>.areStraight(): Boolean {
    val sortedRanks = map { it.rank.value }.sortedDescending()
    return sortedRanks.zipWithNext().all { (a, b) -> a - 1 == b }
}

fun Collection<Card>.areWheelStraight() = map { it.rank }.areWheelStraight()

fun List<Card>.areSuited(): Boolean = map { it.suit }.distinct().count() == 1