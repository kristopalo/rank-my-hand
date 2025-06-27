package com.bigstackbully.rankmyhand.testdata

import com.bigstackbully.rankmyhand.model.command.HandEvaluationCommand

fun composeHandEvaluationCommandForFullHouse(): HandEvaluationCommand =
    HandEvaluationCommand(hand = composeHandOfFullHouse())