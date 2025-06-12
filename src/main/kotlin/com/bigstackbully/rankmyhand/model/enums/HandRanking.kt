package com.bigstackbully.rankmyhand.model.enums

// TODO Kristo @ 12.06.2025 -> Maybe rename this to HandCategory
enum class HandRanking(val strength: Int) {
    HIGH_CARD(strength = 1),
    ONE_PAIR(strength = 2),
    TWO_PAIR(strength = 3),
    THREE_OF_A_KIND(strength = 4),
    STRAIGHT(strength = 5),
    FLUSH(strength = 6),
    FULL_HOUSE(strength = 7),
    FOUR_OF_A_KIND(strength = 8),
    STRAIGHT_FLUSH(strength = 9),
    ROYAL_FLUSH(strength = 10)
}