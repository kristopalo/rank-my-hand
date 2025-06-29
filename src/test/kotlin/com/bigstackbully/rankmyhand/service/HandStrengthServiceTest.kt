package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.HandStrength
import com.bigstackbully.rankmyhand.model.enums.Ranking.*
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class HandStrengthServiceTest() : ShouldSpec({

    val handCombinationService = HandCombinationService()
    val handStrengthService = HandStrengthService(handCombinationService = handCombinationService)

    context("calculate hand strengths for the strongest hands of each ranking") {
        // arrange
        val testCases = mapOf(
            ROYAL_FLUSH to Pair("AKQJT", HandStrength(1, 1.0, 1, 1.0)),
            STRAIGHT_FLUSH to Pair("KQJT9", HandStrength(2, 0.999866, 1, 1.0)),
            FOUR_OF_A_KIND to Pair("AAAAK", HandStrength(11, 0.998660, 1, 1.0)),
            FULL_HOUSE to Pair("AAAKK", HandStrength(167, 0.977754, 1, 1.0)),
            FLUSH to Pair("AKQJ9", HandStrength(323, 0.956848, 1, 1.0)),
            STRAIGHT to Pair("AKQJT", HandStrength(1600, 0.785714, 1, 1.0)),
            THREE_OF_A_KIND to Pair("AAAKQ", HandStrength(1610, 0.784374, 1, 1.0)),
            TWO_PAIR to Pair("AAKKQ", HandStrength(2468, 0.669392, 1, 1.0)),
            ONE_PAIR to Pair("AAKQJ", HandStrength(3326, 0.554409, 1, 1.0)),
            HIGH_CARD to Pair("AKQJ9", HandStrength(6186, 0.171134, 1, 1.0))
        )

        testCases.forEach { (ranking, rankingHandStrengthPair) ->
            // arrange
            val (shortNotation, expHandStrength) = rankingHandStrengthPair

            should("correctly calculate hand strength -> ranking: '$ranking' | hand: '$shortNotation'") {
                // act
                val actHandStrength = handStrengthService.calculateHandStrength(
                    ranking = ranking,
                    shortNotation = shortNotation
                )

                // assert
                actHandStrength.shouldBe(expHandStrength)
            }
        }
    }

    context("calculate hand strengths for the weakest hands of each ranking") {
        // arrange
        val testCases = mapOf(
            ROYAL_FLUSH to Pair("AKQJT", HandStrength(1, 1.0, 1, 1.0)),
            STRAIGHT_FLUSH to Pair("5432A", HandStrength(10, 0.998794, 9, 0.111111)),
            FOUR_OF_A_KIND to Pair("22223", HandStrength(166, 0.977888, 156, 0.00641)),
            FULL_HOUSE to Pair("22233", HandStrength(322, 0.956982, 156, 0.00641)),
            FLUSH to Pair("75432", HandStrength(1599, 0.785848, 1277, 0.000783)),
            STRAIGHT to Pair("5432A", HandStrength(1609, 0.784508, 10, 0.1)),
            THREE_OF_A_KIND to Pair("22243", HandStrength(2467, 0.669526, 858, 0.001166)),
            TWO_PAIR to Pair("33224", HandStrength(3325, 0.554543, 858, 0.001166)),
            ONE_PAIR to Pair("22543", HandStrength(6185, 0.171268, 2860, 0.00035)),
            HIGH_CARD to Pair("75432", HandStrength(7462, 0.000134, 1277, 0.000783))
        )

        testCases.forEach { (ranking, rankingHandStrengthPair) ->
            // arrange
            val (shortNotation, expHandStrength) = rankingHandStrengthPair

            should("correctly calculate hand strength -> ranking: '$ranking' | hand: '$shortNotation'") {
                // act
                val actHandStrength = handStrengthService.calculateHandStrength(
                    ranking = ranking,
                    shortNotation = shortNotation
                )

                // assert
                actHandStrength.shouldBe(expHandStrength)
            }
        }
    }
})
