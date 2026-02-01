package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.EvaluationResult
import com.bigstackbully.rankmyhand.model.response.EvaluationResponse
import org.springframework.stereotype.Service

@Service
class EvaluationResultTransformer {

    fun toResponse(evaluationResult: EvaluationResult): EvaluationResponse = EvaluationResponse(
        hand = evaluationResult.hand,
        ranking = evaluationResult.ranking,
        shortNotation = evaluationResult.shortNotation,
        serializedValue = evaluationResult.serializedValue,
        absoluteStrength = evaluationResult.handStrength.absoluteStrength,
        relativeStrength = evaluationResult.handStrength.relativeStrength
    )
}