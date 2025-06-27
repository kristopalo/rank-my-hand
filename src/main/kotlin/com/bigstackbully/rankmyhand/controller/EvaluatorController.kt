package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.request.HandEvaluationRequest
import com.bigstackbully.rankmyhand.model.response.HandEvaluationResultResponse
import com.bigstackbully.rankmyhand.service.EvaluationRequestTransformer
import com.bigstackbully.rankmyhand.service.EvaluationResultTransformer
import com.bigstackbully.rankmyhand.service.EvaluatorService
import org.springframework.web.bind.annotation.CrossOrigin
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

    @CrossOrigin(origins = [])
    @PostMapping("/evaluate-hand")
    fun evaluateHand(@RequestBody evalReq: HandEvaluationRequest): HandEvaluationResultResponse {
        val evalHandCmd = evaluationRequestTransformer.toCommand(evaluateHandReq = evalReq)
        val evalResult = evaluatorService.evaluate(evaluateHandCmd = evalHandCmd)
        return evaluationResultTransformer.toResponse(evaluationResult = evalResult)
    }
}