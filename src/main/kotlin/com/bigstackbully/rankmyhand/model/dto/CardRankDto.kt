package com.bigstackbully.rankmyhand.model.dto

import com.bigstackbully.rankmyhand.model.enums.Rank

data class CardRankDto(
    val key: String,
    val name: String,
    val value: Int
) {
    companion object {
        fun of(rank: Rank) = with(rank) {
            CardRankDto(
                key = key,
                name = name,
                value = value
            )
        }
    }
}