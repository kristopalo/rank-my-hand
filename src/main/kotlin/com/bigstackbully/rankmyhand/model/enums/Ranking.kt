package com.bigstackbully.rankmyhand.model.enums

import com.bigstackbully.rankmyhand.model.enums.interfaces.KeyedEnum

enum class Ranking(val strength: Int) : KeyedEnum {
    HIGH_CARD(strength = 1),
    ONE_PAIR(strength = 2),
    TWO_PAIR(strength = 3),
    THREE_OF_A_KIND(strength = 4),
    STRAIGHT(strength = 5),
    FLUSH(strength = 6),
    FULL_HOUSE(strength = 7),
    FOUR_OF_A_KIND(strength = 8),
    STRAIGHT_FLUSH(strength = 9),
    ROYAL_FLUSH(strength = 10);

    override val key: String = name
        .split("_").joinToString(separator = "") { it.first().uppercase() }
    val displayName: String = name
        .split("_").joinToString(separator = " ") { "${it.first().uppercase()}${it.substring(1).lowercase()}" }
}