package com.bigstackbully.rankmyhand.model.dto

import com.bigstackbully.rankmyhand.model.enums.Ranking

data class RankingDto(
    val key: String,
    val name: String,
    val displayName: String,
    val strength: Int
) {

    companion object {
        fun of(handRanking: Ranking) = with(handRanking) {
            RankingDto(
                key = key,
                name = name,
                displayName = displayName,
                strength = strength
            )
        }
    }
}
