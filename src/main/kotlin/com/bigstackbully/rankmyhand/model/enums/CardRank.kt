package com.bigstackbully.rankmyhand.model.enums

enum class CardRank(val key: String, val value: Int) {
    LOW_ACE(key = "A", value = 1),
    TWO(key = "2", value = 2),
    THREE(key = "3", value = 3),
    FOUR(key = "4", value = 4),
    FIVE(key = "5", value = 5),
    SIX(key = "6", value = 6),
    SEVEN(key = "7", value = 7),
    EIGHT(key = "8", value = 8),
    NINE(key = "9", value = 9),
    TEN(key = "T", value = 10),
    JACK(key = "J", value = 11),
    QUEEN(key = "Q", value = 12),
    KING(key = "K", value = 13),
    ACE(key = "A", value = 14);
}