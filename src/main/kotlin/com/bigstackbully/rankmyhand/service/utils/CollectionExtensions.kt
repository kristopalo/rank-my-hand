package com.bigstackbully.rankmyhand.service.utils

import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.PlayingCard

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