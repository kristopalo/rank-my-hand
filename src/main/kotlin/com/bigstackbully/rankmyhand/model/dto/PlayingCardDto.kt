package com.bigstackbully.rankmyhand.model.dto

data class PlayingCardDto(
    val code: String,
    val rank: String,
    val suit: String,
    val enumName: String,
    val displayName: String,
    val value: Int
)
