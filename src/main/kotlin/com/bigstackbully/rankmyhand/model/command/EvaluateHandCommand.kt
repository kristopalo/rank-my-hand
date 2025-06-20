package com.bigstackbully.rankmyhand.model.command

import com.bigstackbully.rankmyhand.model.Hand

data class EvaluateHandCommand(
    val hand: Hand
)