package com.bigstackbully.rankmyhand.domain.service

import com.bigstackbully.rankmyhand.domain.model.EvaluationResult
import com.bigstackbully.rankmyhand.domain.model.Hand
import com.bigstackbully.rankmyhand.domain.model.enums.CardRank
import com.bigstackbully.rankmyhand.domain.model.enums.HandRank
import org.springframework.stereotype.Service

@Service
class HandEvaluatorService {

    fun evaluate(hand: Hand): EvaluationResult {
        val result: EvaluationResult

        if (isRoyalFlush(hand))
            result = EvaluationResult(HandRank.ROYAL_FLUSH)
        else
            result = EvaluationResult(HandRank.HIGH_CARD)

        return result
    }

    fun isRoyalFlush(hand: Hand): Boolean {
        val cards = hand.cards
        val ranks = cards.map { card -> card.rank }

        return hand.numberOfCards == 5 &&
            hand.isSuited &&
            ranks.contains(CardRank.ACE) &&
            ranks.contains(CardRank.KING) &&
            ranks.contains(CardRank.QUEEN) &&
            ranks.contains(CardRank.JACK) &&
            ranks.contains(CardRank.TEN)
    }
}