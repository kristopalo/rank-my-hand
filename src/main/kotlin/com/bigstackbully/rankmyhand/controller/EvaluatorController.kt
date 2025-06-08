package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.request.EvaluateHandRequest
import com.bigstackbully.rankmyhand.model.response.EvaluationResultResponse
import com.bigstackbully.rankmyhand.service.EvaluationRequestTransformer
import com.bigstackbully.rankmyhand.service.EvaluationResultTransformer
import com.bigstackbully.rankmyhand.service.EvaluatorService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/evaluator")
class EvaluatorController(
    private val evaluationRequestTransformer: EvaluationRequestTransformer,
    private val evaluatorService: EvaluatorService,
    private val evaluationResultTransformer: EvaluationResultTransformer
) {

    @PostMapping("/evaluate-hand")
    fun evaluate(@RequestBody evalReq: EvaluateHandRequest): EvaluationResultResponse {
        val evalHandCmd = evaluationRequestTransformer.toCommand(evaluateHandReq = evalReq)
        val evalResult = evaluatorService.evaluate(evaluateHandCmd = evalHandCmd)
        return evaluationResultTransformer.toResponse(evaluationResult = evalResult)
    }
}