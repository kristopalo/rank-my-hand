package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.request.EvaluationRequest
import com.bigstackbully.rankmyhand.model.response.EvaluationResponse
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

    @PostMapping("/evaluate")
    fun evaluateHand(@RequestBody evalReq: EvaluationRequest): EvaluationResponse {
        val evaluationCmd = evaluationRequestTransformer.toCommand(evaluationReq = evalReq)
        val evalResult = evaluatorService.evaluate(evaluateHandCmd = evaluationCmd)
        return evaluationResultTransformer.toResponse(evaluationResult = evalResult)
    }
}