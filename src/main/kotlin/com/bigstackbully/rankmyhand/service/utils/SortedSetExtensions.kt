package com.bigstackbully.rankmyhand.service.utils

import com.bigstackbully.rankmyhand.model.RankUnit
import com.bigstackbully.rankmyhand.model.combination.WHEEL_STRAIGHT_SERIALIZED_VALUE
import com.bigstackbully.rankmyhand.model.combination.WHEEL_STRAIGHT_SHORT_NOTATION
import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.HandRanking
import java.util.SortedSet

fun SortedSet<RankUnit>.maxUnitSize(): Int? = maxOfOrNull { it.cards.size }

fun SortedSet<RankUnit>.areSuited(): Boolean = flatMap { cg -> cg.suits }.distinct().size == 1

fun SortedSet<RankUnit>.areInConsecutiveDescOrder(): Boolean = map { it.rank.value }
    .zipWithNext()
    .all { (a, b) -> a - 1 == b }

fun SortedSet<RankUnit>.highestRank(): CardRank? = maxOfOrNull { it.rank }

fun SortedSet<RankUnit>.serializedValue(): String {
    if (isWheelStraight())
        return WHEEL_STRAIGHT_SERIALIZED_VALUE

    return joinToString(separator = "-") { it.totalValue.toString() }
}

fun SortedSet<RankUnit>.ranks(): List<CardRank> = map { it.rank }

fun SortedSet<RankUnit>.rankingWithSerializedValue(ranking: HandRanking): String =
    "${ranking.strength}-${serializedValue()}"

fun SortedSet<RankUnit>.standardNotation(): String {
    if (isWheelStraight()) {
        val aceRankUnit = first { it.rank == CardRank.ACE }
        val otherRankUnits = filterNot { it.rank == CardRank.ACE }.toSortedSet()
        return "${otherRankUnits.standardNotation()} ${aceRankUnit.standardNotation}"
    }

    return joinToString(separator = " ") { it.standardNotation }
}

fun SortedSet<RankUnit>.isWheelStraight(): Boolean {
    return ranks().containsAll(
        listOf(
            CardRank.FIVE,
            CardRank.FOUR,
            CardRank.THREE,
            CardRank.TWO,
            CardRank.ACE
        )
    )
}

fun SortedSet<RankUnit>.shortNotation(): String {
    if (isWheelStraight())
        return WHEEL_STRAIGHT_SHORT_NOTATION

    return joinToString(separator = "") { it.shortNotation }
}