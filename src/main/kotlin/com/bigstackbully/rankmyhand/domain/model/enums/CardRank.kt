package com.bigstackbully.rankmyhand.domain.model.enums

enum class CardRank(val abbreviation: String, val values: List<Int>) {
    TWO("2", listOf(2)),
    THREE("3", listOf(3)),
    FOUR("4", listOf(4)),
    FIVE("5", listOf(5)),
    SIX("6", listOf(6)),
    SEVEN("7", listOf(7)),
    EIGHT("8", listOf(8)),
    NINE("9", listOf(9)),
    TEN("10", listOf(10)),
    JACK("J", listOf(11)),
    QUEEN("Q", listOf(12)),
    KING("K", listOf(13)),
    ACE("A", listOf(1, 14)); // Ace has dual values
}