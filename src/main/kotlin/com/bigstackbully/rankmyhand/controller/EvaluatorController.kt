package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.request.EvaluateStringInputRequest
import com.bigstackbully.rankmyhand.model.response.EvaluationResultResponse
import com.bigstackbully.rankmyhand.service.EvaluationRequestTransformer
import com.bigstackbully.rankmyhand.service.EvaluationResultTransformer
import com.bigstackbully.rankmyhand.service.EvaluatorService
import org.springframework.web.bind.annotation.GetMapping
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

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello world!"
    }

    @PostMapping("/evaluate-cards-as-string-input")
    fun evaluate(@RequestBody evalReq: EvaluateStringInputRequest): EvaluationResultResponse {
        val evalCmd = evaluationRequestTransformer.toCommand(evaluateCardsReq = evalReq)
        val evalResult = evaluatorService.evaluate(evaluateCardsCmd = evalCmd)
        return evaluationResultTransformer.toResponse(evaluationResult = evalResult)
    }
}