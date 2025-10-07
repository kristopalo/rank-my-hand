package com.bigstackbully.rankmyhand.model.response

import com.bigstackbully.rankmyhand.model.enums.Ranking

data class EvaluationResponse(
    val hand: String,
    val ranking: Ranking,
    val shortNotation: String,
    val serializedValue: String,
    val absolutePosition: Int,
    val absoluteStrength: Double,
    val relativePosition: Int,
    val relativeStrength: Double
)