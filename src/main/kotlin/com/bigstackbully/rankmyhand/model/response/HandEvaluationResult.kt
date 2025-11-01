package com.bigstackbully.rankmyhand.model.response

import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.HandStrength
import com.bigstackbully.rankmyhand.model.enums.Ranking

data class HandEvaluationResult(
    val hand: Hand,
    val ranking: Ranking,
    val handStrength: HandStrength
)