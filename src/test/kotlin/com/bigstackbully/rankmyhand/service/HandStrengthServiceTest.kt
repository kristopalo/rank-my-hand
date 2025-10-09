package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.HandStrength
import com.bigstackbully.rankmyhand.model.enums.Ranking.*
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import kotlin.random.Random

class HandStrengthServiceTest {

    val rankingService = RankingService()
    val handCombinationService = HandCombinationService(rankingService)
    val handStrengthService = HandStrengthService(handCombinationService = handCombinationService)

    @Test
    fun `should calculate hand strengths for strongest hands of each ranking`() {
        // arrange
        val testCases = mapOf(
            ROYAL_FLUSH to Pair("AKQJTs", HandStrength(1, 1.0, 1, 1.0)),
            STRAIGHT_FLUSH to Pair("KQJT9s", HandStrength(2, 0.999866, 1, 1.0)),
            FOUR_OF_A_KIND to Pair("AAAAKo", HandStrength(11, 0.998660, 1, 1.0)),
            FULL_HOUSE to Pair("AAAKKo", HandStrength(167, 0.977754, 1, 1.0)),
            FLUSH to Pair("AKQJ9s", HandStrength(323, 0.956848, 1, 1.0)),
            STRAIGHT to Pair("AKQJTo", HandStrength(1600, 0.785714, 1, 1.0)),
            THREE_OF_A_KIND to Pair("AAAKQo", HandStrength(1610, 0.784374, 1, 1.0)),
            TWO_PAIR to Pair("AAKKQo", HandStrength(2468, 0.669392, 1, 1.0)),
            ONE_PAIR to Pair("AAKQJo", HandStrength(3326, 0.554409, 1, 1.0)),
            HIGH_CARD to Pair("AKQJ9o", HandStrength(6186, 0.171134, 1, 1.0))
        )

        testCases.forEach { (ranking, rankingHandStrengthPair) ->
            // arrange
            val (shortNotation, expHandStrength) = rankingHandStrengthPair

            // act
            val actHandStrength = handStrengthService.calculateHandStrength(
                ranking = ranking,
                shorthandNotation = shortNotation
            )

            // assert
            actHandStrength.shouldBe(expHandStrength)
        }
    }

    @Test
    fun `should calculate hand strengths for weakest hands of each ranking`() {
        // arrange
        val testCases = mapOf(
            ROYAL_FLUSH to Pair("AKQJTs", HandStrength(1, 1.0, 1, 1.0)),
            STRAIGHT_FLUSH to Pair("5432As", HandStrength(10, 0.998794, 9, 0.111111)),
            FOUR_OF_A_KIND to Pair("22223o", HandStrength(166, 0.977888, 156, 0.00641)),
            FULL_HOUSE to Pair("22233o", HandStrength(322, 0.956982, 156, 0.00641)),
            FLUSH to Pair("75432s", HandStrength(1599, 0.785848, 1277, 0.000783)),
            STRAIGHT to Pair("5432Ao", HandStrength(1609, 0.784508, 10, 0.1)),
            THREE_OF_A_KIND to Pair("22243o", HandStrength(2467, 0.669526, 858, 0.001166)),
            TWO_PAIR to Pair("33224o", HandStrength(3325, 0.554543, 858, 0.001166)),
            ONE_PAIR to Pair("22543o", HandStrength(6185, 0.171268, 2860, 0.00035)),
            HIGH_CARD to Pair("75432o", HandStrength(7462, 0.000134, 1277, 0.000783))
        )

        testCases.forEach { (ranking, rankingHandStrengthPair) ->
            // arrange
            val (shortNotation, expHandStrength) = rankingHandStrengthPair

            // act
            val actHandStrength = handStrengthService.calculateHandStrength(
                ranking = ranking,
                shorthandNotation = shortNotation
            )

            // assert
            actHandStrength.shouldBe(expHandStrength)

        }
    }
}
