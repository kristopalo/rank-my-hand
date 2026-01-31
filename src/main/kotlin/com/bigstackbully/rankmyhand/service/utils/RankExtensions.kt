package com.bigstackbully.rankmyhand.service.utils

import com.bigstackbully.rankmyhand.model.enums.Rank
import com.bigstackbully.rankmyhand.model.enums.Rank.Companion.sortByRankCountThenByRankValueComparator
import com.bigstackbully.rankmyhand.utils.EMPTY_STRING

fun List<Rank>.toSortedListGroupedByRankCountAndThenByRankValue(
    comparator: Comparator<Map.Entry<Rank, List<Rank>>> = sortByRankCountThenByRankValueComparator
) = groupBy { it }
    .entries
    .sortedWith(comparator)
    .flatMap { it.value }

fun List<Rank>.toRankNotation(): String =
    toSortedListGroupedByRankCountAndThenByRankValue().joinToString(separator = EMPTY_STRING)

fun List<Rank>.areWheelStraight() = hasFiveItems() && containsAll(
    listOf(
        Rank.FIVE,
        Rank.FOUR,
        Rank.THREE,
        Rank.TWO,
        Rank.ACE
    )
)

fun List<Rank>.hasFiveItems(): Boolean = count() == 5