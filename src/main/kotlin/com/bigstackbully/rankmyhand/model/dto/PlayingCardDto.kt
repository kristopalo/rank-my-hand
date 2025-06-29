package com.bigstackbully.rankmyhand.model.dto

data class PlayingCardDto(
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
)
