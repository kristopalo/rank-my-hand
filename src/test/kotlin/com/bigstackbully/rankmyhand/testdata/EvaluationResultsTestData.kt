package com.bigstackbully.rankmyhand.testdata

import com.bigstackbully.rankmyhand.model.HandEvaluationResult
import com.bigstackbully.rankmyhand.model.HandStrength
import com.bigstackbully.rankmyhand.model.enums.Ranking.FULL_HOUSE

fun composeHandEvaluationResultForFullHouse(): HandEvaluationResult = HandEvaluationResult(
    hand = "As Ah Ad Ks Kh",
    ranking = FULL_HOUSE,
    serializedValue = "7-42-26",
    shortNotation = "AAAKK",
    handStrength = HandStrength(
        absolutePosition = 167,
        absoluteStrength = 0.977754,
        relativePosition = 1,
        relativeStrength = 1.0
    )
)
