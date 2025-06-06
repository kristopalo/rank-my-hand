package com.bigstackbully.rankmyhand.model.enums

enum class CardRank(val abbreviation: String, val value: Int) {
    // TODO ACE_LOW
    ACE_LOW(abbreviation = "A", value = 1),
    TWO(abbreviation = "2", value = 2),
    THREE(abbreviation = "3", value = 3),
    FOUR(abbreviation = "4", value = 4),
    FIVE(abbreviation = "5", value = 5),
    SIX(abbreviation = "6", value = 6),
    SEVEN(abbreviation = "7", value = 7),
    EIGHT(abbreviation = "8", value = 8),
    NINE(abbreviation = "9", value = 9),
    TEN(abbreviation = "T", value = 10),
    JACK(abbreviation = "J", value = 11),
    QUEEN(abbreviation = "Q", value = 12),
    KING(abbreviation = "K", value = 13),
    // TODO ACE_HIGH
    ACE(abbreviation = "A", value = 14); // Ace has dual values

    companion object {
        fun fromAbbreviation(abbr: String): CardRank? {
            return entries.find { it.abbreviation == abbr }
        }
    }
}