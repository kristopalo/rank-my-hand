package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.EvaluationResult
import com.bigstackbully.rankmyhand.model.response.EvaluationResultResponse
import org.springframework.stereotype.Service

@Service
class EvaluationResultTransformer {

    fun toResponse(evaluationResult: EvaluationResult): EvaluationResultResponse = EvaluationResultResponse(
        hand = evaluationResult.hand,
        rank = evaluationResult.handRank,
        serializedValue = evaluationResult.serializedValue,
        handInShortNotation = evaluationResult.shortNotation
    )
}