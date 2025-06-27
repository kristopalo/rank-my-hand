package com.bigstackbully.rankmyhand.testdata

import com.bigstackbully.rankmyhand.model.request.HandEvaluationRequest

fun composeHandEvaluationRequestForFullHouse(): HandEvaluationRequest =
    HandEvaluationRequest(hand = "As Ah Ad Ks Kh")