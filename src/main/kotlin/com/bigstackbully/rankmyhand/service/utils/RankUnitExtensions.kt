package com.bigstackbully.rankmyhand.service.utils

import com.bigstackbully.rankmyhand.model.RankUnit
import com.bigstackbully.rankmyhand.model.enums.Rank
import com.bigstackbully.rankmyhand.model.notation.RankNotation
import com.bigstackbully.rankmyhand.model.notation.SignatureNotation
import com.bigstackbully.rankmyhand.model.notation.StandardNotation
import com.bigstackbully.rankmyhand.utils.HYPHEN
import java.util.*

fun SortedSet<RankUnit>.maxUnitSize(): Int? = maxOfOrNull { it.cards.size }

fun SortedSet<RankUnit>.areSuited(): Boolean = flatMap { it.suits }.distinct().size == 1

fun SortedSet<RankUnit>.areStraight(): Boolean = map { it.rank.value }
    .zipWithNext()
    .all { (a, b) -> a - 1 == b }

fun SortedSet<RankUnit>.highestRank(): Rank? = maxOfOrNull { it.rank }

fun SortedSet<RankUnit>.serializedValue(): String = joinToString(separator = HYPHEN) { it.totalValue.toString() }

fun SortedSet<RankUnit>.toRankNotation(): RankNotation = RankNotation.from(this)

fun SortedSet<RankUnit>.toSignatureNotation(): SignatureNotation = SignatureNotation.of(this)

fun SortedSet<RankUnit>.toStandardNotation(): StandardNotation = StandardNotation.of(this)

fun SortedSet<RankUnit>.ranks(): List<Rank> = map { it.rank }