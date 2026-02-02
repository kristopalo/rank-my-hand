package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.request.EvaluationRequest
import com.bigstackbully.rankmyhand.model.response.EvaluationResponse
import com.bigstackbully.rankmyhand.service.EvaluationRequestTransformer
import com.bigstackbully.rankmyhand.service.EvaluationResultTransformer
import com.bigstackbully.rankmyhand.service.EvaluatorService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/evaluator")
@Tag(name = "Evaluator", description = "Poker hand evaluation API")
class EvaluatorController(
    private val evaluationRequestTransformer: EvaluationRequestTransformer,
    private val evaluatorService: EvaluatorService,
    private val evaluationResultTransformer: EvaluationResultTransformer
) {

    @PostMapping("/evaluate")
    @Operation(
        summary = "Evaluate a poker hand",
        description = "Evaluates a poker hand by providing hole cards and board cards. Returns the hand ranking, strength, and details."
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Successfully evaluated the hand",
                content = [Content(schema = Schema(implementation = EvaluationResponse::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid input - cards format is incorrect or contains invalid cards"
            )
        ]
    )
    fun evaluateHand(@RequestBody evalReq: EvaluationRequest): EvaluationResponse {
        val evalCmd = evaluationRequestTransformer.toCommand(evaluationReq = evalReq)
        val evalResult = evaluatorService.evaluate(evaluationCmd = evalCmd)
        return evaluationResultTransformer.toResponse(evaluationResult = evalResult)
    }
}