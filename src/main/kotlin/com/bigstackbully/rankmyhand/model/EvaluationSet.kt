package com.bigstackbully.rankmyhand.model

import java.util.SortedSet

data class EvaluationSet(
    val cardGroups: SortedSet<CardGroup> = sortedSetOf<CardGroup>()
) {

    val groupCount: Int = cardGroups.size
    val maxGroupSize: Int? = cardGroups.maxOfOrNull { it.numberOfCards }
    val isSameSuit: Boolean = cardGroups.flatMap { cg -> cg.suits }.distinct().size == 1
}
