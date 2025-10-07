package com.bigstackbully.rankmyhand.testdata

import com.bigstackbully.rankmyhand.testdata.model.EvaluationContext

fun composeHandEvaluationContextForFullHouse(): EvaluationContext = EvaluationContext(
    request = composeEvaluationRequestForFullHouse(),
    command = composeEvaluationCommandForFullHouse(),
    result = composeEvaluationResultForFullHouse(),
    response = composeEvaluationResponseForFullHouse()
)