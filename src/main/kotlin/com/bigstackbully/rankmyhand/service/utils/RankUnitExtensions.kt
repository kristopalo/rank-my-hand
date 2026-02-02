package com.bigstackbully.rankmyhand.service.utils

import com.bigstackbully.rankmyhand.model.RankUnit
import com.bigstackbully.rankmyhand.model.enums.Rank
import com.bigstackbully.rankmyhand.model.notation.RankNotation
import com.bigstackbully.rankmyhand.model.notation.StandardNotation
import com.bigstackbully.rankmyhand.utils.HYPHEN
import java.util.*

fun SortedSet<RankUnit>.areSuited(): Boolean = flatMap { it.suits }.distinct().count() == 1

fun SortedSet<RankUnit>.highestRank(): Rank? = maxOfOrNull { it.rank }

fun SortedSet<RankUnit>.serializedValue(): String = joinToString(separator = HYPHEN) { it.totalValue.toString() }

fun SortedSet<RankUnit>.toRankNotation(): String = RankNotation.from(this).toString()

fun SortedSet<RankUnit>.toStandardNotation(): String = StandardNotation.of(this).toString()

fun SortedSet<RankUnit>.ranks(): List<Rank> = map { it.rank }