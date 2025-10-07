package com.bigstackbully.rankmyhand.model.command

import com.bigstackbully.rankmyhand.model.enums.PlayingCard

data class EvaluationCommand(
    val cards: List<PlayingCard>
)