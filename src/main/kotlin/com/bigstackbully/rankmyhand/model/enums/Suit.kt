package com.bigstackbully.rankmyhand.model.enums

enum class Suit(val shortNotation: String, val color: SuitColor) {
    HEARTS(shortNotation = "h", color = SuitColor.RED),
    SPADES(shortNotation = "s", color = SuitColor.BLACK),
    DIAMONDS(shortNotation = "d", color = SuitColor.RED),
    CLUBS(shortNotation = "c", color = SuitColor.BLACK);
}
