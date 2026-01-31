package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.characteristic.HasCards
import com.bigstackbully.rankmyhand.model.enums.Card

data class EvaluationContext(
    val holeCards: List<Card>,
    val boardCards: List<Card>
) : HasCards {
    override val cards: List<Card> = holeCards + boardCards
}
