package com.bigstackbully.rankmyhand.model.enums

enum class HandRank(val value: Int) {
    HIGH_CARD(value = 1),
    ONE_PAIR(value = 2),
    TWO_PAIR(value = 3),
    THREE_OF_A_KIND(value = 4),
    STRAIGHT(value = 5),
    FLUSH(value = 6),
    FULL_HOUSE(value = 7),
    FOUR_OF_A_KIND(value = 8),
    STRAIGHT_FLUSH(value = 9),
    ROYAL_FLUSH(value = 10)
}