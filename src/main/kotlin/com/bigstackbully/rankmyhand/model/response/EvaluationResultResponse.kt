package com.bigstackbully.rankmyhand.model.response

import com.bigstackbully.rankmyhand.model.enums.HandRanking

data class EvaluationResultResponse(
    val hand: String,
    val ranking: HandRanking,
    val shortNotation: String,
    val serializedValue: String,
    val absolutePosition: Int,
    val absoluteStrength: Double,
    val relativePosition: Int,
    val relativeStrength: Double
)