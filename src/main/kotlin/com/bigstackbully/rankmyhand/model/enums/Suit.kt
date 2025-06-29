package com.bigstackbully.rankmyhand.model.enums

enum class Suit(
    val standardNotation: String,
    val emoji: String
) {
    SPADES(standardNotation = "s", emoji = "♠\uFE0F"),
    HEARTS(standardNotation = "h", emoji = "♥\uFE0F"),
    DIAMONDS(standardNotation = "d", emoji = "♦\uFE0F"),
    CLUBS(standardNotation = "c", emoji = "♣\uFE0F");

    val key = standardNotation.uppercase();
}
