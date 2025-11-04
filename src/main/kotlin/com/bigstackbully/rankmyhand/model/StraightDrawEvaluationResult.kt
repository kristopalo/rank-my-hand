package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.combination.HandCombination
import com.bigstackbully.rankmyhand.model.enums.PlayingCard

data class StraightDrawEvaluationResult(
    val handContext: HandContext,
    val targetHandCombination: HandCombination,
    val matchingCards: List<PlayingCard>,
    val outs: List<PlayingCard>
) {

    val matchingRankKeys: List<String> = matchingCards.map { it.rank.key }.distinct()
    val numberOfMatchingRankKeys = matchingRankKeys.size
    val numberOfCardsNeeded = 5 - numberOfMatchingRankKeys

}