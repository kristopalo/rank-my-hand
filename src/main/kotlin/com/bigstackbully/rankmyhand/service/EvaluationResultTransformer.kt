package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.EvaluationResult
import com.bigstackbully.rankmyhand.model.response.EvaluationResultResponse
import org.springframework.stereotype.Service

@Service
class EvaluationResultTransformer {

    fun toResponse(evaluationResult: EvaluationResult): EvaluationResultResponse = EvaluationResultResponse(
        rank = evaluationResult.rank
    )
}