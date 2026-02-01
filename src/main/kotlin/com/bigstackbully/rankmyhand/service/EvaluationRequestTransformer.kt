package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.EvaluationContext
import com.bigstackbully.rankmyhand.model.command.EvaluationCommand
import com.bigstackbully.rankmyhand.model.enums.Card
import com.bigstackbully.rankmyhand.model.request.EvaluationRequest
import com.bigstackbully.rankmyhand.service.utils.areUnique
import com.bigstackbully.rankmyhand.service.utils.hasEvenNumberOfCharacters
import com.bigstackbully.rankmyhand.service.utils.wrapInApostrophes
import com.bigstackbully.rankmyhand.utils.ITEM_SEPARATOR
import com.bigstackbully.rankmyhand.utils.SINGLE_SPACE
import org.springframework.stereotype.Service

@Service
class EvaluationRequestTransformer {

    fun toCommand(evaluationReq: EvaluationRequest): EvaluationCommand {
        val input = evaluationReq.cards

        val invalidCharacters = input.filter {
            !it.isLetterOrDigit() && !it.isWhitespace() && it !in ALLOWED_SEPARATORS
        }.toSet()

        require(invalidCharacters.isEmpty()) {
            buildList {
                add("Input '$input' contains these invalid characters: [${invalidCharacters.joinToString(ITEM_SEPARATOR) { it.wrapInApostrophes() }}].")
                add(
                    "Only alphanumeric characters, whitespace, and the following item separators are allowed: [${
                        ALLOWED_SEPARATORS.joinToString(ITEM_SEPARATOR) { it.wrapInApostrophes() }
                    }]."
                )
            }.joinToString(separator = SINGLE_SPACE)
        }

        val filteredInput = input.filter { it.isLetterOrDigit() }

        require(filteredInput.isNotEmpty()) {
            buildList {
                add("Unable to parse any valid cards from the input string of '$input'.")
                add("Cards must be represented in standard notation by specifying exactly two characters per card: rank + suit.")
                add("Valid ranks: A (Ace), K (King), Q (Queen), J (Jack), T (Ten), 9, 8, 7, 6, 5, 4, 3, 2.")
                add("Valid suits: s (spades), h (hearts), d (diamonds), c (clubs).")
                add("Valid separators between cards are: space, hyphen (-), comma (,), semicolon (;), period (.), pipe (|).")
                add("All non-alphanumeric characters will be purged when parsing input string to cards.")
                add("Examples of valid inputs: 'As Kh Qd Jc Ts' (with spaces), 'AsKhQdJcTs' (without spaces), 'As-Kh-Qd-Jc-Ts' (with separators).")
            }.joinToString(separator = SINGLE_SPACE)
        }

        require(filteredInput.hasEvenNumberOfCharacters()) {
            "Input string can only contain an equal number of characters, 2 for each card."
        }

        val validCards = mutableListOf<Card>()
        val invalidCardsInStandardNt = mutableListOf<String>()

        for (chunk in filteredInput.chunked(2)) {
            val standardNt = "${chunk[0].uppercase()}${chunk[1].lowercase()}"
            val card = Card.fromStandardNotation(standardNotation = standardNt)

            if (card != null) {
                validCards.add(card)
            } else {
                invalidCardsInStandardNt.add(standardNt)
            }
        }

        require(invalidCardsInStandardNt.isEmpty()) {
            "Unable to parse the following items into cards: ${invalidCardsInStandardNt.joinToString(ITEM_SEPARATOR) { it.wrapInApostrophes() }}"
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

    companion object {
        private val ALLOWED_SEPARATORS = setOf('-', ',', ';', '.', '|')
    }
}