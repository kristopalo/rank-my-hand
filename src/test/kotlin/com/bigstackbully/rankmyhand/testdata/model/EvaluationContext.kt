package com.bigstackbully.rankmyhand.testdata.model

import com.bigstackbully.rankmyhand.model.EvaluationResult
import com.bigstackbully.rankmyhand.model.command.EvaluationCommand
import com.bigstackbully.rankmyhand.model.request.EvaluationRequest
import com.bigstackbully.rankmyhand.model.response.EvaluationResponse

data class EvaluationContext(
    val request: EvaluationRequest,
    val command: EvaluationCommand,
    val result: EvaluationResult,
    val response: EvaluationResponse
)
