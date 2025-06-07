package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.command.EvaluateCardsCommand
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.model.request.EvaluateCardsRequest
import com.bigstackbully.rankmyhand.service.utils.hasEvenNumberOfCharacters
import org.springframework.stereotype.Service

@Service
class EvaluationRequestTransformer {

    fun toCommand(evaluateCardsReq: EvaluateCardsRequest): EvaluateCardsCommand {
        val input = evaluateCardsReq.input
        val filteredInput = input.filter { it.isLetterOrDigit() }

        require(filteredInput.hasEvenNumberOfCharacters()) {
            throw IllegalArgumentException("Input string can only contain an equal number of characters, 2 for each pair.")
        }

        val cards = filteredInput
            .chunked(2)
            .mapNotNull { it ->
                val abbr = "${it[0].uppercase()}${it[1].lowercase()}"
                PlayingCard.fromAbbreviation(abbreviation = abbr)
            }
            .toSet()

        return EvaluateCardsCommand(
            cards = cards
        )
    }
}