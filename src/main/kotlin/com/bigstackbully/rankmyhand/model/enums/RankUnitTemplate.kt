package com.bigstackbully.rankmyhand.model.enums

enum class RankUnitTemplate(
    val requiredCount: Int
) {
    SINGLE(requiredCount = 1),
    PAIR(requiredCount = 2),
    THREE_OF_A_KIND(requiredCount = 3),
    FOUR_OF_A_KIND(requiredCount = 4);
}