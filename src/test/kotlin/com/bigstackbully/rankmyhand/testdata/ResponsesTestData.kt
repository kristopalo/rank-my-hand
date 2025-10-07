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
    absolutePosition = handEvalResult.handStrength.absolutePosition,
    absoluteStrength = handEvalResult.handStrength.absoluteStrength,
    relativePosition = handEvalResult.handStrength.relativePosition,
    relativeStrength = handEvalResult.handStrength.relativeStrength
)