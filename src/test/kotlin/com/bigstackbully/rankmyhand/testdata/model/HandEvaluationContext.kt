package com.bigstackbully.rankmyhand.testdata.model

import com.bigstackbully.rankmyhand.model.HandEvaluationResult
import com.bigstackbully.rankmyhand.model.command.HandEvaluationCommand
import com.bigstackbully.rankmyhand.model.request.HandEvaluationRequest
import com.bigstackbully.rankmyhand.model.response.HandEvaluationResultResponse

data class HandEvaluationContext(
    val request: HandEvaluationRequest,
    val command: HandEvaluationCommand,
    val result: HandEvaluationResult,
    val response: HandEvaluationResultResponse
)
