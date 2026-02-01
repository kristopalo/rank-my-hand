package com.bigstackbully.rankmyhand.testdata

import com.bigstackbully.rankmyhand.model.EvaluationResult
import com.bigstackbully.rankmyhand.model.response.EvaluationResponse

fun composeEvaluationResponseForFullHouse(): EvaluationResponse {
    return composeHandEvaluationResponse(
        handEvalResult = composeEvaluationResultForFullHouse()
    )
}

fun composeHandEvaluationResponse(handEvalResult: EvaluationResult) = EvaluationResponse(
    hand = handEvalResult.hand,
    ranking = handEvalResult.ranking,
    shortNotation = handEvalResult.shortNotation,
    serializedValue = handEvalResult.serializedValue,
    absoluteStrength = handEvalResult.handStrength.absoluteStrength,
    relativeStrength = handEvalResult.handStrength.relativeStrength
)