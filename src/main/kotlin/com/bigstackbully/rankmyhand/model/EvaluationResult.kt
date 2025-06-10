package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.enums.HandRank

data class EvaluationResult(
    val hand: String,
    val handRank: HandRank,
    val serializedValue: String,
    val shortNotation: String
)