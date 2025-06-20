package com.bigstackbully.rankmyhand.service.utils

import com.bigstackbully.rankmyhand.model.RankUnit
import com.bigstackbully.rankmyhand.model.enums.CardRank
import java.util.*

fun SortedSet<RankUnit>.maxUnitSize(): Int? = maxOfOrNull { it.cards.size }

fun SortedSet<RankUnit>.areSuited(): Boolean = flatMap { cg -> cg.suits }.distinct().size == 1

fun SortedSet<RankUnit>.areInConsecutiveDescOrder(): Boolean = map { it.rank.value }
    .zipWithNext()
    .all { (a, b) -> a - 1 == b }

fun SortedSet<RankUnit>.highestRank(): CardRank? = maxOfOrNull { it.rank }

fun SortedSet<RankUnit>.serializedValue(): String = joinToString(separator = "-") { it.totalValue.toString() }

fun SortedSet<RankUnit>.standardNotation(): String = joinToString(separator = " ") { it.standardNotation }

fun SortedSet<RankUnit>.shortNotation(): String = joinToString(separator = "") { it.shortNotation }