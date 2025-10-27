package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.enums.Ranking
import com.bigstackbully.rankmyhand.model.notation.SignatureNotation
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class HandCombinationServiceTest {

    private val rankingService = RankingService()
    private val handCombinationService = HandCombinationService(rankingService)

    @ParameterizedTest
    @CsvSource(
        "J7o, HIGH_CARD, J7432",
        "JJ753o, ONE_PAIR, JJ753",
        "JJ975o, ONE_PAIR, JJ975",
        "JJQ97o, ONE_PAIR, JJQ97",
        "2222o, FOUR_OF_A_KIND, 22223",
        "2223o, THREE_OF_A_KIND, 22243",
        "222o, THREE_OF_A_KIND, 22243",
        "JJTTo, TWO_PAIR, JJTT2",
        "JJo, ONE_PAIR, JJ432",
        "KQs, HIGH_CARD, KQ432",
        "KQo, HIGH_CARD, KQ432",
        "Ks, HIGH_CARD, K5432",
        "7543s, HIGH_CARD, 75432",
        "7543o, HIGH_CARD, 75432",
        "754o, HIGH_CARD, 75432",
        "75o, HIGH_CARD, 75432",
        "7s, HIGH_CARD, 75432",
    )
    fun `should find the worst possible hand combination for a given signature notation`(
        signatureNotation: String,
        expRanking: Ranking,
        expCardsInStandardNotation: String
    ) {
        // arrange
        val signatureNt = SignatureNotation.from(signatureNotation)

        // act
        val actHandCombination = handCombinationService.findWorstPossibleHandCombination(signatureNt)

        // assert
        with(actHandCombination) {
            shouldNotBeNull()
            ranking.shouldBe(expRanking)
            hand.shouldBe(expCardsInStandardNotation)
        }
    }
}