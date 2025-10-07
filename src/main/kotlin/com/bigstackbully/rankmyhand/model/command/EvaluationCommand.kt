package com.bigstackbully.rankmyhand.model.command

import com.bigstackbully.rankmyhand.model.Hand

data class EvaluationCommand(
    val hands: List<Hand>
)