package com.bigstackbully.rankmyhand.model.enums

enum class Suit(val abbreviation: String, val color: SuitColor) {
    HEARTS(abbreviation = "h", color = SuitColor.RED),
    SPADES(abbreviation = "s", color = SuitColor.BLACK),
    DIAMONDS(abbreviation = "d", color = SuitColor.RED),
    CLUBS(abbreviation = "c", color = SuitColor.BLACK);

    companion object {
        fun fromAbbreviation(abbr: String): Suit? {
            return entries.find { it.abbreviation == abbr }
        }
    }
}
