package com.bigstackbully.rankmyhand.model.combination

import com.bigstackbully.rankmyhand.model.enums.Ranking

data class HandCombination(
    val hand: String,
    val isSuited: Boolean,
    val ranking: Ranking,
    val absolutePosition: Int,
    val absoluteStrength: Double,
    val relativePosition: Int,
    val relativeStrength: Double,
    val rankCounts: List<Int>
)