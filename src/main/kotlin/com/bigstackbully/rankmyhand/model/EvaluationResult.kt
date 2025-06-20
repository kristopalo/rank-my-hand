package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.enums.HandRanking

data class EvaluationResult(
    val hand: String,
    val handRanking: HandRanking,
    val serializedValue: String,
    val shortNotation: String,
    val handStrength: HandStrength
)