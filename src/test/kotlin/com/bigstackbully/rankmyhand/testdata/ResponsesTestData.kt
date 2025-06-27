package com.bigstackbully.rankmyhand.testdata

import com.bigstackbully.rankmyhand.model.HandEvaluationResult
import com.bigstackbully.rankmyhand.model.response.HandEvaluationResultResponse

fun composeEvaluationResultResponseForFullHouse(): HandEvaluationResultResponse {
    return composeEvaluationResultResponse(
        handEvalResult = composeHandEvaluationResultForFullHouse()
    )
}

fun composeEvaluationResultResponse(handEvalResult: HandEvaluationResult) = HandEvaluationResultResponse(
    hand = handEvalResult.hand,
    ranking = handEvalResult.ranking,
    shortNotation = handEvalResult.shortNotation,
    serializedValue = handEvalResult.serializedValue,
    absolutePosition = handEvalResult.handStrength.absolutePosition,
    absoluteStrength = handEvalResult.handStrength.absoluteStrength,
    relativePosition = handEvalResult.handStrength.relativePosition,
    relativeStrength = handEvalResult.handStrength.relativeStrength
)