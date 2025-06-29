package com.bigstackbully.rankmyhand.model.dto

import com.bigstackbully.rankmyhand.model.enums.Ranking

data class HandRankingDto(
    val key: String,
    val name: String,
    val displayName: String,
    val strength: Int
) {

    companion object {
        fun of(handRanking: Ranking): HandRankingDto {
            return with(handRanking) {
                HandRankingDto(
                    key = key,
                    name = name,
                    displayName = displayName,
                    strength = strength
                )
            }
        }
    }
}
