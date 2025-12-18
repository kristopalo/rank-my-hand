package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.EvaluationResult
import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.EvaluationContext
import com.bigstackbully.rankmyhand.model.command.EvaluationCommand
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.model.response.HandEvaluationResult
import org.springframework.stereotype.Service

@Service
class EvaluatorService(
    private val rankingService: RankingService,
    private val handStrengthService: HandStrengthService,
    private val drawService: DrawService
) {

    fun evaluate(evaluationCmd: EvaluationCommand): EvaluationResult {
        val handContext = evaluationCmd.evaluationContext

        val bestHandEvalResult = findBestHand(handContext)
        // TODO Kristo @ 02.11.2025 -> Find all possible / feasible ways to improve this hand and provide them with the probabilities
        val potentialDraws = drawService.evaluatePotentialDraws(handContext)

        with(bestHandEvalResult) {
            return EvaluationResult(
                hand = hand.standardNotation.toString(),
                ranking = ranking,
                serializedValue = hand.serializedValue,
                shortNotation = hand.rankNotation.toString(),
                handStrength = handStrength
            )
        }
    }

    fun findBestHand(evaluationContext: EvaluationContext): HandEvaluationResult {
        val cards = evaluationContext.cards
        val allPossibleHands = if (cards.size >= 5) cards.allFiveCardHands() else listOf(Hand.of(cards))

        return allPossibleHands
            .map { hand -> evaluateHand(hand = hand) }
            .maxBy { it.handStrength.absoluteStrength }
    }

    fun evaluateHand(hand: Hand): HandEvaluationResult {
        val ranking = rankingService.evaluateRanking(hand)
        val handStrength = handStrengthService.calculateHandStrength(
            ranking = ranking,
            hand = hand
        )

        return HandEvaluationResult(
            hand = hand,
            ranking = ranking,
            handStrength = handStrength,
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