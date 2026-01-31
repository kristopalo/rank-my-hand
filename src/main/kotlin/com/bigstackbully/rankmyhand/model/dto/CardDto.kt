package com.bigstackbully.rankmyhand.model.dto

import com.bigstackbully.rankmyhand.model.enums.Card

data class CardDto(
    val key: String,
    val name: String,
    val rank: String,
    val rankKey: String,
    val rankValue: Int,
    val suit: String,
    val suitKey: String,
    val suitEmoji: String,
    val displayName: String,
    val standardNotation: String
) {
    companion object {
        fun of(card: Card) = with(card) {
            CardDto(
                key = key,
                name = name,
                rank = rank.name,
                rankKey = rank.key,
                rankValue = rank.value,
                suit = suit.name,
                suitKey = suit.key,
                suitEmoji = suit.emoji,
                displayName = displayName,
                standardNotation = standardNotation
            )
        }
    }
}
