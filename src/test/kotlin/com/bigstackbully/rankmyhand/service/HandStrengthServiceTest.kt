package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.HandStrength
import com.bigstackbully.rankmyhand.model.enums.Card
import com.bigstackbully.rankmyhand.model.enums.Ranking
import com.bigstackbully.rankmyhand.model.enums.Ranking.*
import com.bigstackbully.rankmyhand.utils.SINGLE_SPACE
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class HandStrengthServiceTest {

    private val rankingService = RankingService()
    private val handCombinationService = HandCombinationService(rankingService)
    private val handStrengthService = HandStrengthService(handCombinationService = handCombinationService)

    private fun createHandFromStandardNotation(standardNotationString: String): Hand {
        val cards = standardNotationString.split(SINGLE_SPACE).mapNotNull { Card.fromStandardNotation(it) }
        return Hand.of(cards)
    }

    @Test
    fun `should calculate hand strength for the strongest hand combination of each ranking`() {
        // arrange
        val testCases = mapOf(
            ROYAL_FLUSH to Pair("As Ks Qs Js Ts", HandStrength(1, 1.0, 1, 1.0)),
            STRAIGHT_FLUSH to Pair("Ks Qs Js Ts 9s", HandStrength(2, 0.999866, 1, 1.0)),
            FOUR_OF_A_KIND to Pair("As Ah Ad Ac Ks", HandStrength(11, 0.998660, 1, 1.0)),
            FULL_HOUSE to Pair("As Ah Ad Kc Kd", HandStrength(167, 0.977754, 1, 1.0)),
            FLUSH to Pair("As Ks Qs Js 9s", HandStrength(323, 0.956848, 1, 1.0)),
            STRAIGHT to Pair("As Ks Qs Js Ts", HandStrength(1600, 0.785714, 1, 1.0)),
            THREE_OF_A_KIND to Pair("As Ah Ad Ks Qs", HandStrength(1610, 0.784374, 1, 1.0)),
            TWO_PAIR to Pair("As Ah Kc Kd Qs", HandStrength(2468, 0.669392, 1, 1.0)),
            ONE_PAIR to Pair("As Ah Ks Qs Js", HandStrength(3326, 0.554409, 1, 1.0)),
            HIGH_CARD to Pair("As Ks Qs Js 9s", HandStrength(6186, 0.171134, 1, 1.0))
        )

        assertHandStrengths(testCases)
    }

    @Test
    fun `should calculate hand strength for the weakest hand combination of each ranking`() {
        // arrange
        val testCases = mapOf(
            ROYAL_FLUSH to Pair("As Ks Qs Js Ts", HandStrength(1, 1.0, 1, 1.0)),
            STRAIGHT_FLUSH to Pair("5s 4s 3s 2s As", HandStrength(10, 0.998794, 9, 0.111111)),
            FOUR_OF_A_KIND to Pair("2s 2h 2d 2c 3s", HandStrength(166, 0.977888, 156, 0.00641)),
            FULL_HOUSE to Pair("2s 2h 2d 3c 3s", HandStrength(322, 0.956982, 156, 0.00641)),
            FLUSH to Pair("7s 5s 4s 3s 2s", HandStrength(1599, 0.785848, 1277, 0.000783)),
            STRAIGHT to Pair("5s 4s 3s 2s As", HandStrength(1609, 0.784508, 10, 0.1)),
            THREE_OF_A_KIND to Pair("2s 2h 2d 4s 3s", HandStrength(2467, 0.669526, 858, 0.001166)),
            TWO_PAIR to Pair("3s 3h 2c 2d 4s", HandStrength(3325, 0.554543, 858, 0.001166)),
            ONE_PAIR to Pair("2s 2h 5s 4s 3s", HandStrength(6185, 0.171268, 2860, 0.00035)),
            HIGH_CARD to Pair("7s 5s 4s 3s 2s", HandStrength(7462, 0.000134, 1277, 0.000783))
        )

        assertHandStrengths(testCases)
    }

    private fun assertHandStrengths(testCases: Map<Ranking, Pair<String, HandStrength>>) {
        testCases.forEach { (ranking, rankingHandStrengthPair) ->
            // arrange
            val (stdNt, expHandStrength) = rankingHandStrengthPair
            val hand = createHandFromStandardNotation(stdNt)

            // act
            val actHandStrength = handStrengthService.calculateHandStrength(
                ranking = ranking,
                hand = hand
            )

            // assert
            actHandStrength shouldBe expHandStrength
        }
    }
}
