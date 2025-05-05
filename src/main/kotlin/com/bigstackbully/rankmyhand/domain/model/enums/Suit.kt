package com.bigstackbully.rankmyhand.domain.model.enums

enum class Suit(val initial: String, val color: SuitColor) {
    HEARTS("H", SuitColor.RED),
    DIAMONDS("D", SuitColor.RED),
    CLUBS("C", SuitColor.BLACK),
    SPADES("S", SuitColor.BLACK)
}
