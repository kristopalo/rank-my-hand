package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.enums.Ranking

data class HandEvaluationResult(
    val hand: String,
    val ranking: Ranking,
    val serializedValue: String,
    val shortNotation: String,
    val handStrength: HandStrength
)