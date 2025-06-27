package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.HandEvaluationResult
import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.command.HandEvaluationCommand
import org.springframework.stereotype.Service

@Service
class EvaluatorService(
    private val rankingService: RankingService,
    private val handStrengthService: HandStrengthService
) {
    fun evaluate(evaluateHandCmd: HandEvaluationCommand): HandEvaluationResult = evaluate(evaluateHandCmd.hand)

    fun evaluate(hand: Hand): HandEvaluationResult {
        val ranking = rankingService.determineRanking(hand)
        val standardNotation = hand.standardNotation
        val rankingWithSerializedValue = "${ranking.strength}-${hand.serializedValue}"
        val shortNotation = hand.shortNotation
        val handStrength = handStrengthService.calculateHandStrength(
            ranking = ranking,
            shortNotation = shortNotation
        )

        return HandEvaluationResult(
            hand = standardNotation,
            ranking = ranking,
            serializedValue = rankingWithSerializedValue,
            shortNotation = shortNotation,
            handStrength = handStrength
        )
    }
}