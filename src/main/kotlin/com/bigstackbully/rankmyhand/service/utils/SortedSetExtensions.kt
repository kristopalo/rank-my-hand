package com.bigstackbully.rankmyhand.service.utils

import com.bigstackbully.rankmyhand.model.RankUnit
import com.bigstackbully.rankmyhand.model.enums.CardRank
import java.util.SortedSet

fun SortedSet<RankUnit>.maxUnitSize(): Int? = maxOfOrNull { it.cards.size }

fun SortedSet<RankUnit>.areSuited(): Boolean = flatMap { cg -> cg.suits }.distinct().size == 1

fun SortedSet<RankUnit>.areInConsecutiveDescOrder(): Boolean = map { it.rank.value }
    .zipWithNext()
    .all { (a, b) -> a - 1 == b }

fun SortedSet<RankUnit>.highestRank(): CardRank? = maxOfOrNull { it.rank }