package com.bigstackbully.rankmyhand.testdata

import com.bigstackbully.rankmyhand.testdata.model.HandEvaluationContext

fun composeHandEvaluationContextForFullHouse(): HandEvaluationContext = HandEvaluationContext(
    request = composeHandEvaluationRequestForFullHouse(),
    command = composeHandEvaluationCommandForFullHouse(),
    result = composeHandEvaluationResultForFullHouse(),
    response = composeEvaluationResultResponseForFullHouse()
)