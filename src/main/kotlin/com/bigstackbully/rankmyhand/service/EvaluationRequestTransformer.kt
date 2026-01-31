package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.EvaluationContext
import com.bigstackbully.rankmyhand.model.command.EvaluationCommand
import com.bigstackbully.rankmyhand.model.enums.Card
import com.bigstackbully.rankmyhand.model.request.EvaluationRequest
import com.bigstackbully.rankmyhand.service.utils.areUnique
import com.bigstackbully.rankmyhand.service.utils.hasEvenNumberOfCharacters
import org.springframework.stereotype.Service

@Service
class EvaluationRequestTransformer {

    fun toCommand(evaluationReq: EvaluationRequest): EvaluationCommand {
        val input = evaluationReq.cards
        val filteredInput = input.filter { it.isLetterOrDigit() }

        require(filteredInput.hasEvenNumberOfCharacters()) {
            "Input string can only contain an equal number of characters, 2 for each card."
        }

        val validCards = mutableListOf<Card>()
        val invalidCardsInStandardNt = mutableListOf<String>()

        for (chunk in filteredInput.chunked(2)) {
            val standardNt = "${chunk[0].uppercase()}${chunk[1].lowercase()}"
            val card = Card.fromShortNotation(standardNotation = standardNt)

            if (card != null) {
                validCards.add(card)
            } else {
                invalidCardsInStandardNt.add(standardNt)
            }
        }

        require(invalidCardsInStandardNt.isEmpty()) {
            "Found the following invalid cards in the input: ${invalidCardsInStandardNt.joinToString(", ") { card -> "'$card'" }}"
        }

        require(validCards.areUnique()) {
            "All cards in the provided hand have to be unique."
        }

        require(validCards.isNotEmpty()) {
            "At least one card must be provided."
        }

        val evaluationContext = EvaluationContext(
            holeCards = validCards.take(2),
            boardCards = validCards.drop(2)
        )

        return EvaluationCommand(
            evaluationContext = evaluationContext
        )
    }
}