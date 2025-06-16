package com.bigstackbully.rankmyhand.model.combination

import com.bigstackbully.rankmyhand.model.enums.HandRanking

data class HandCombination(
    val hand: String,
    val ranking: HandRanking,
    val absolutePosition: Int,
    val absoluteStrength: Double,
    val relativePosition: Int,
    val relativeStrength: Double
)