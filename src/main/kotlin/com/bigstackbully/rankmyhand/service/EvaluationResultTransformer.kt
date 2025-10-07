package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.EvaluationResult
import com.bigstackbully.rankmyhand.model.response.EvaluationResponse
import org.springframework.stereotype.Service

@Service
class EvaluationResultTransformer {

    fun toResponse(evaluationResult: EvaluationResult): EvaluationResponse = EvaluationResponse(
        hand = evaluationResult.hand,
        ranking = evaluationResult.ranking,
        serializedValue = evaluationResult.serializedValue,
        shortNotation = evaluationResult.shortNotation,
        absolutePosition = evaluationResult.handStrength.absolutePosition,
        absoluteStrength = evaluationResult.handStrength.absoluteStrength,
        relativePosition = evaluationResult.handStrength.relativePosition,
        relativeStrength = evaluationResult.handStrength.relativeStrength
    )
}