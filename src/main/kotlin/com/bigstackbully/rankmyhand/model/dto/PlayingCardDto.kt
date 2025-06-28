package com.bigstackbully.rankmyhand.model.dto

data class PlayingCardDto(
    val code: String,
    val enum: String,
    val rank: String,
    val value: Int,
    val suit: String,
    val suitEmoji: String,
    val displayName: String
)
