package com.bigstackbully.rankmyhand.model.command

import com.bigstackbully.rankmyhand.model.enums.PlayingCard

data class EvaluateHandCommand(
    val cards: Set<PlayingCard> = setOf()
)