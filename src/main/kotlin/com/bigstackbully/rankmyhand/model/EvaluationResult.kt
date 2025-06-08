package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.enums.HandRank

data class EvaluationResult(
    val hand: String,
    val rank: HandRank
)