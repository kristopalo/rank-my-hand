package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.CardGroup
import com.bigstackbully.rankmyhand.model.command.EvaluateCardsCommand
import com.bigstackbully.rankmyhand.model.EvaluationResult
import com.bigstackbully.rankmyhand.model.EvaluationSet
import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.HandRank
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import org.springframework.stereotype.Service

@Service
class EvaluatorService {

    fun evaluate(evaluateCardsCmd: EvaluateCardsCommand): EvaluationResult {
        val availableCards = evaluateCardsCmd.cards.toList()

        val allPossibleFiveCardHands = mutableListOf<List<PlayingCard>>()

        // TODO Kristo @ 07.06.2025 -> Find all possible 5-card combinations out of 7 available cards. Should result to 21 combinations.
        for (i in 0 until availableCards.size - 1) {
            for (j in (i + 1) until availableCards.size) {
                val excludedIndices = setOf(i, j)
                val currentFiveCardHand = availableCards.filterIndexed { index, _ -> index !in excludedIndices }
                allPossibleFiveCardHands.add(currentFiveCardHand)
            }
        }

        return EvaluationResult(HandRank.HIGH_CARD)
    }

    /*

    Available cards:
    As Ah Ad Ks Kh Qs Qh


    ### Find all possible 5-card combinations: ###

    position 0 is anchor, rest are shifting, starting from anchor  pos +1
    __ __ Ad Ks Kh Qs Qh
    __ Ah __ Ks Kh Qs Qh
    __ Ah Ad __ Kh Qs Qh
    __ Ah Ad Ks __ Qs Qh
    __ Ah Ad Ks Kh __ Qh
    __ Ah Ad Ks Kh Qs __

    position 1 is anchor, rest are shifting
    As __ __ Ks Kh Qs Qh
    As __ Ad __ Kh Qs Qh
    As __ Ad Ks __ Qs Qh
    As __ Ad Ks Kh __ Qh
    As __ Ad Ks Kh Qs __

    position 2 is anchor, rest are shifting
    As Ah __ __ Kh Qs Qh
    As Ah __ Ks __ Qs Qh
    As Ah __ Ks Kh __ Qh
    As Ah __ Ks Kh Qs __

    position 3 is anchor, rest are shifting
    As Ah Ad __ __ Qs Qh
    As Ah Ad __ Kh __ Qh
    As Ah Ad __ Kh Qs __

    position 4 is anchor, rest are shifting
    As Ah Ad Ks __ __ Qh
    As Ah Ad Ks __ Qs __

    position 5 is anchor, with only 1 possible combination
    As Ah Ad Ks Kh __ __

    Result:
    __ __ Ad Ks Kh Qs Qh
    __ Ah __ Ks Kh Qs Qh
    __ Ah Ad __ Kh Qs Qh
    __ Ah Ad Ks __ Qs Qh
    __ Ah Ad Ks Kh __ Qh
    __ Ah Ad Ks Kh Qs __
    As __ __ Ks Kh Qs Qh
    As __ Ad __ Kh Qs Qh
    As __ Ad Ks __ Qs Qh
    As __ Ad Ks Kh __ Qh
    As __ Ad Ks Kh Qs __
    As Ah __ __ Kh Qs Qh
    As Ah __ Ks __ Qs Qh
    As Ah __ Ks Kh __ Qh
    As Ah __ Ks Kh Qs __
    As Ah Ad __ __ Qs Qh
    As Ah Ad __ Kh __ Qh
    As Ah Ad __ Kh Qs __
    As Ah Ad Ks __ __ Qh
    As Ah Ad Ks __ Qs __
    As Ah Ad Ks Kh __ __

     */

    fun evaluateHand(hand: Hand): EvaluationResult {
        val result: EvaluationResult

        val cardGroups = hand.cards
            .groupBy { it.rank }
            .map { (_, cards) -> CardGroup.of(cards) }
            .toSortedSet()

        val evaluationSet = EvaluationSet(
            cardGroups = cardGroups
        )

        val groupCount = evaluationSet.groupCount

        result = when (groupCount) {
            2 -> handleEvaluationSetOfTwoCardGroups()
            3 -> handleEvaluationSetOfThreeCardGroups()
            4 -> handleEvaluationSetOfFourCardGroups()
            5 -> handleEvaluationSetOfFiveCardGroups()
            else -> EvaluationResult(HandRank.HIGH_CARD) // TODO Kristo @ 04.06.2025 -> Just a hack here.
        }

        return result
    }

    private fun handleEvaluationSetOfFourCardGroups(): EvaluationResult {
        TODO("Not yet implemented")
    }

    private fun handleEvaluationSetOfThreeCardGroups(): EvaluationResult {
        TODO("Not yet implemented")
    }

    private fun handleEvaluationSetOfTwoCardGroups(): EvaluationResult {
        TODO("Not yet implemented")
    }

    private fun handleEvaluationSetOfFiveCardGroups(): EvaluationResult {
        TODO("Not yet implemented")
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