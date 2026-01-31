package com.bigstackbully.rankmyhand.model.dto

import com.bigstackbully.rankmyhand.model.enums.Suit

data class SuitDto(
    val key: String,
    val name: String,
    val standardNotation: String,
    val emoji: String
) {
    companion object {
        fun of(suit: Suit) = with(suit) {
            SuitDto(
                key = key,
                name = name,
                standardNotation = standardNotation,
                emoji = emoji
            )
        }
    }}
