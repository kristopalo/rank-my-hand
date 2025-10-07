package com.bigstackbully.rankmyhand.testdata

import com.bigstackbully.rankmyhand.model.request.EvaluationRequest

fun composeEvaluationRequestForFullHouse(): EvaluationRequest =
    EvaluationRequest(cards = "As Ah Ad Ks Kh")