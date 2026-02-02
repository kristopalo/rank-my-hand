package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.enums.Card
import com.bigstackbully.rankmyhand.model.enums.Ranking
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RankingServiceTest {

    private val rankingService = RankingService()

    @ParameterizedTest
    @CsvSource(
        "As Ks Qs Js Ts,    ROYAL_FLUSH",
        "Kh Qh Jh Th 9h,    STRAIGHT_FLUSH",
        "As Ah Ad Ac Ks,    FOUR_OF_A_KIND",
        "As Ah Ad Ac,       FOUR_OF_A_KIND",
        "As Ah Ad Kd Ks,    FULL_HOUSE",
        "As Ks Qs Js 9s,    FLUSH",
        "As Ks Qh Jh Ts,    STRAIGHT",
        "As Ah Ad Kd Qs,    THREE_OF_A_KIND",
        "As Ah Ad Kd,       THREE_OF_A_KIND",
        "As Ah Ad,          THREE_OF_A_KIND",
        "As Ah Kd Kc Qs,    TWO_PAIR",
        "As Ah Kd Kc,       TWO_PAIR",
        "As Ah Kd Qs Js,    ONE_PAIR",
        "As Ah Kd Qs,       ONE_PAIR",
        "As Ah Kd,          ONE_PAIR",
        "As Ah,             ONE_PAIR",
        "As Ks Qd Jc 9s,    HIGH_CARD",
        "As Ks Qd Jc,       HIGH_CARD",
        "As Ks Qd,          HIGH_CARD",
        "As Ks,             HIGH_CARD",
        "As,                HIGH_CARD"
    )
    fun `should correctly rank each hand`(
        cardsInStandardNotation: String,
        expRanking: Ranking
    ) {
        // arrange
        val cards = cardsInStandardNotation
            .filter { it.isLetterOrDigit() }
            .chunked(2)
            .mapNotNull { Card.fromStandardNotation(standardNotation = "${it[0].uppercase()}${it[1].lowercase()}") }

        val hand = Hand.of(cards)

        // act
        val actRanking = rankingService.evaluateRanking(hand)

        // assert
        actRanking shouldBe expRanking
    }

    @Test
    fun `should throw an illegal argument exception when trying to rank an empty hand`() {
        // arrange
        val hand = Hand.of(emptyList())

        // act
        val actException = assertThrows<IllegalArgumentException> {
            rankingService.evaluateRanking(hand)
        }

        // assert
        actException.message shouldBe "Cannot evaluate the ranking of an empty hand."
    }
}