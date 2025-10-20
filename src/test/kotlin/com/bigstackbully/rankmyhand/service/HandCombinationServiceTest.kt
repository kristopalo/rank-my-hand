package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.model.enums.Ranking
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class HandCombinationServiceTest {

//    private val rankingService = RankingService()
//    private val handCombinationService = HandCombinationService(rankingService)
//
//    @ParameterizedTest
//    @CsvSource(
//        "2223, THREE_OF_A_KIND, 22243",
//        "222, THREE_OF_A_KIND, 22243",
//        "22, ONE_PAIR, 22543",
//        "7543, HIGH_CARD, 75432",
//        "754, HIGH_CARD, 75432",
//        "75, HIGH_CARD, 75432",
//        "7, HIGH_CARD, 75432",
//    )
//    fun `should find the worst possible hand combination for a given hand`(
//        signatureNotation: String,
//        expRanking: Ranking,
//        expCardsInStandardNotation: String
//    ) {
//        // arrange
//        val cards = signatureNotation
//            .filter { it.isLetterOrDigit() }
//            .chunked(2)
//            .mapNotNull { it ->
//                val standardNotation = "${it[0].uppercase()}${it[1].lowercase()}"
//                PlayingCard.fromShortNotation(standardNotation = standardNotation)
//            }
//
//        val hand = Hand.of(cards)
//
//        // act
//        val actHandCombination = handCombinationService.findWorstPossibleHandCombination(hand)
//
//        // assert
//        actHandCombination.shouldNotBeNull()
//        actHandCombination.ranking.shouldBe(expRanking)
//        actHandCombination.hand.shouldBe(expCardsInStandardNotation)
//    }
}