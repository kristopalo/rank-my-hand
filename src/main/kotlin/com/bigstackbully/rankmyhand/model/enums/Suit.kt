package com.bigstackbully.rankmyhand.model.enums

enum class Suit(
    val shortNotation: String,
    val emoji: String
) {
    SPADES(shortNotation = "s", emoji = "♠\uFE0F"),
    HEARTS(shortNotation = "h", emoji = "♥\uFE0F"),
    DIAMONDS(shortNotation = "d", emoji = "♦\uFE0F"),
    CLUBS(shortNotation = "c", emoji = "♣\uFE0F");
}
