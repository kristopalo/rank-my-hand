package com.bigstackbully.rankmyhand.model.dto

import com.bigstackbully.rankmyhand.model.combination.HandCombination

data class HandCombinationDto(
    val hand: String,
    val isSuited: Boolean,
    val ranking: String,
    val absolutePosition: Int,
    val absoluteStrength: Double,
    val relativePosition: Int,
    val relativeStrength: Double
) {
    companion object {
        fun of(handCombination: HandCombination) = with(handCombination) {
            HandCombinationDto(
                hand = hand,
                isSuited = isSuited,
                ranking = ranking.name,
                absolutePosition = absolutePosition,
                absoluteStrength = absoluteStrength,
                relativePosition = relativePosition,
                relativeStrength = relativeStrength
            )
        }
    }
}
