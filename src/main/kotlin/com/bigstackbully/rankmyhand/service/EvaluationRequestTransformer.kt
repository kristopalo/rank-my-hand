package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.command.EvaluationCommand
import com.bigstackbully.rankmyhand.model.command.HandEvaluationCommand
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.model.request.EvaluationRequest
import com.bigstackbully.rankmyhand.service.utils.areUnique
import com.bigstackbully.rankmyhand.service.utils.hasEvenNumberOfCharacters
import org.springframework.stereotype.Service

@Service
class EvaluationRequestTransformer {

    fun toCommand(evaluationReq: EvaluationRequest): HandEvaluationCommand {
        val input = evaluationReq.cards
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

        require(cards.areUnique()) {
            throw IllegalArgumentException("All cards in the provided hand have to be unique.")
        }

        require(cards.isNotEmpty()) {
            throw IllegalArgumentException("At least one card must be provided.")
        }

        val allPossibleHands = cards.allFiveCardHands()

        return EvaluationCommand(
            hands = allPossibleHands
        )
    }

    fun List<PlayingCard>.allFiveCardHands() = combinations(5)
        .map { cards -> Hand.of(cards) }

    fun <PlayingCard> List<PlayingCard>.combinations(k: Int): Sequence<List<PlayingCard>> = sequence {
        if (k == 0) {
            yield(emptyList())
        } else {
            for (i in indices) {
                val head = this@combinations[i]
                val tail = drop(i + 1)
                for (combo in tail.combinations(k - 1)) {
                    yield(listOf(head) + combo)
                }
            }
        }
    }
}