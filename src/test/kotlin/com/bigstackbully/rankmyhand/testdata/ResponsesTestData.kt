package com.bigstackbully.rankmyhand.testdata

import com.bigstackbully.rankmyhand.model.HandEvaluationResult
import com.bigstackbully.rankmyhand.model.response.HandEvaluationResponse

fun composeHandEvaluationResponseForFullHouse(): HandEvaluationResponse {
    return composeHandEvaluationResponse(
        handEvalResult = composeHandEvaluationResultForFullHouse()
    )
}

fun composeHandEvaluationResponse(handEvalResult: HandEvaluationResult) = HandEvaluationResponse(
    hand = handEvalResult.hand,
    ranking = handEvalResult.ranking,
    shortNotation = handEvalResult.shortNotation,
    serializedValue = handEvalResult.serializedValue,
    absolutePosition = handEvalResult.handStrength.absolutePosition,
    absoluteStrength = handEvalResult.handStrength.absoluteStrength,
    relativePosition = handEvalResult.handStrength.relativePosition,
    relativeStrength = handEvalResult.handStrength.relativeStrength
)