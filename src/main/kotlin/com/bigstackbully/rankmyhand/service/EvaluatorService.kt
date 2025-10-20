package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.EvaluationResult
import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.command.EvaluationCommand
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import org.springframework.stereotype.Service

@Service
class EvaluatorService(
    private val rankingService: RankingService,
    private val handStrengthService: HandStrengthService
) {
    fun evaluate(evaluationCmd: EvaluationCommand): EvaluationResult {
        val cards = evaluationCmd.cards

        val allPossibleHands = if (cards.size >= 5) {
            cards.allFiveCardHands()
        } else {
            listOf(Hand.of(cards))
        }

        return allPossibleHands
            .map { hand -> evaluate(hand = hand) }
            .maxBy { it.handStrength.absoluteStrength }
    }

    fun evaluate(hand: Hand): EvaluationResult {
        val ranking = rankingService.evaluateRanking(hand)

        val standardNotation = hand.standardNotation
        val rankingWithSerializedValue = "${ranking.strength}-${hand.serializedValue}"
        val rankNotation = hand.rankNotation

        val handStrength = handStrengthService.calculateHandStrength(
            ranking = ranking,
            rankNotation = rankNotation
        )

        return EvaluationResult(
            hand = standardNotation.toString(),
            ranking = ranking,
            serializedValue = rankingWithSerializedValue,
            shortNotation = rankNotation.toString(),
            handStrength = handStrength
        )
    }

    fun List<PlayingCard>.allFiveCardHands() = combinations(5)
        .map { cards -> Hand.of(cards) }
        .toList()

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