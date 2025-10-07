package com.bigstackbully.rankmyhand.testdata

import com.bigstackbully.rankmyhand.model.request.EvaluationRequest

fun composeHandEvaluationRequestForFullHouse(): EvaluationRequest =
    EvaluationRequest(cards = "As Ah Ad Ks Kh")