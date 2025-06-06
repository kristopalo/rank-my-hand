package com.bigstackbully.rankmyhand.model.enums

enum class Suit(val initial: String, val color: SuitColor) {
    HEARTS(initial = "H", color = SuitColor.RED),
    SPADES(initial = "S", color = SuitColor.BLACK),
    DIAMONDS(initial = "D", color = SuitColor.RED),
    CLUBS(initial = "C", color = SuitColor.BLACK)
}
