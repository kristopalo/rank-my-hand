package com.bigstackbully.rankmyhand.model.dto

import com.bigstackbully.rankmyhand.model.combination.HandCombination
import kotlin.Int

data class HandCombinationDto(
    val hand: String,
    val handRanking: String,
    val absolutePosition: Int,
    val absoluteStrength: Double,
    val relativePosition: Int,
    val relativeStrength: Double
) {
    companion object {
        fun of(handCombination: HandCombination): HandCombinationDto {
            return with(handCombination) {
                HandCombinationDto(
                    hand = hand,
                    handRanking = ranking.name,
                    absolutePosition = absolutePosition,
                    absoluteStrength = absoluteStrength,
                    relativePosition = relativePosition,
                    relativeStrength = relativeStrength
                )
            }
        }
    }
}
