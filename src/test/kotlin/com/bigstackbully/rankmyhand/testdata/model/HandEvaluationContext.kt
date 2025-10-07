package com.bigstackbully.rankmyhand.testdata.model

import com.bigstackbully.rankmyhand.model.HandEvaluationResult
import com.bigstackbully.rankmyhand.model.command.HandEvaluationCommand
import com.bigstackbully.rankmyhand.model.request.EvaluationRequest
import com.bigstackbully.rankmyhand.model.response.EvaluationResponse

data class HandEvaluationContext(
    val request: EvaluationRequest,
    val command: HandEvaluationCommand,
    val result: HandEvaluationResult,
    val response: EvaluationResponse
)
