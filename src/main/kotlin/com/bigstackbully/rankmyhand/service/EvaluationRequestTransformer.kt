package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.command.EvaluateHandCommand
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.model.request.EvaluateHandRequest
import com.bigstackbully.rankmyhand.service.utils.areUnique
import com.bigstackbully.rankmyhand.service.utils.hasEvenNumberOfCharacters
import org.springframework.stereotype.Service

@Service
class EvaluationRequestTransformer {

    fun toCommand(evaluateHandReq: EvaluateHandRequest): EvaluateHandCommand {
        val input = evaluateHandReq.hand
        val filteredInput = input.filter { it.isLetterOrDigit() }

        require(filteredInput.hasEvenNumberOfCharacters()) {
            throw IllegalArgumentException("Input string can only contain an equal number of characters, 2 for each pair.")
        }

        val cards = filteredInput
            .chunked(2)
            .mapNotNull { it ->
                val standardNotation = "${it[0].uppercase()}${it[1].lowercase()}"
                PlayingCard.fromShortNotation(standardNotation = standardNotation)
            }

        require(cards.size == 5) {
            throw IllegalArgumentException("Evaluator only accepts a 5-card hand as an input for evaluation.")
        }

        require(cards.areUnique()) {
            throw IllegalArgumentException("All cards in the provided hand have to be unique.")
        }

        return EvaluateHandCommand(
            cards = cards.toSet()
        )
    }
}