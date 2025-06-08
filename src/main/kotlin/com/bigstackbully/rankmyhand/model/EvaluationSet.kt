package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.enums.CardRank
import java.util.SortedSet

data class EvaluationSet(
    val unitsOfEvaluation: SortedSet<RankUnit> = sortedSetOf<RankUnit>()
) {
    val unitCount: Int = unitsOfEvaluation.size
    val maxUnitSize: Int? = unitsOfEvaluation.maxOfOrNull { it.numberOfCards }
    val isSuited: Boolean = unitsOfEvaluation.flatMap { cg -> cg.suits }.distinct().size == 1
    val isStraight: Boolean = unitsOfEvaluation.map { it.rank.value }.zipWithNext().all { (a, b) -> a - 1 == b }
    val highestRank: CardRank? = unitsOfEvaluation.maxOfOrNull { it.rank }
}
