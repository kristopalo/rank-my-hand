package com.bigstackbully.rankmyhand.model.enums

enum class CardRank(val shortNotation: String, val value: Int) {
    // TODO ACE_LOW
    ACE_LOW(shortNotation = "A", value = 1),
    TWO(shortNotation = "2", value = 2),
    THREE(shortNotation = "3", value = 3),
    FOUR(shortNotation = "4", value = 4),
    FIVE(shortNotation = "5", value = 5),
    SIX(shortNotation = "6", value = 6),
    SEVEN(shortNotation = "7", value = 7),
    EIGHT(shortNotation = "8", value = 8),
    NINE(shortNotation = "9", value = 9),
    TEN(shortNotation = "T", value = 10),
    JACK(shortNotation = "J", value = 11),
    QUEEN(shortNotation = "Q", value = 12),
    KING(shortNotation = "K", value = 13),
    // TODO ACE_HIGH
    ACE(shortNotation = "A", value = 14); // Ace has dual values

    companion object {
        fun fromAbbreviation(abbr: String): CardRank? {
            return entries.find { it.shortNotation == abbr }
        }
    }
}