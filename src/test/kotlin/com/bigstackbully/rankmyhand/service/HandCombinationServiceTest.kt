package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.enums.Rank
import com.bigstackbully.rankmyhand.model.enums.Ranking
import com.bigstackbully.rankmyhand.utils.EMPTY_STRING
import com.bigstackbully.rankmyhand.utils.SINGLE_SPACE
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class HandCombinationServiceTest {

    private val rankingService = RankingService()
    private val handCombinationService = HandCombinationService(rankingService)

    @ParameterizedTest
    @CsvSource(
        "Jc 7d 4s 3h 2c, HIGH_CARD, J7432",
        "Jc Jd 7s 5h 3c, ONE_PAIR, JJ753",
        "Jc Jd 9s 7h 5c, ONE_PAIR, JJ975",
        "Jc Jd Qs 9h 7c, ONE_PAIR, JJQ97",
        "2c 2d 2h 2s, FOUR_OF_A_KIND, 22223",
        "2c 2d 2h 3c, THREE_OF_A_KIND, 22243",
        "2c 2d 2h, THREE_OF_A_KIND, 22243",
        "Jc Jd Ts Th, TWO_PAIR, JJTT2",
        "Jc Jd, ONE_PAIR, JJ432",
        "Kc Qc, HIGH_CARD, KQ432",
        "Kc Qd, HIGH_CARD, KQ432",
        "Kc, HIGH_CARD, K5432",
        "7c 5c 4c 3c 2d, HIGH_CARD, 75432",
        "7c 5d 4s 3h, HIGH_CARD, 75432",
        "7c 5d 4s, HIGH_CARD, 75432",
        "7c 5d, HIGH_CARD, 75432",
        "7c, HIGH_CARD, 75432"
    )
    fun `should find the worst possible hand combination for a given hand`(
        cards: String,
        expRanking: Ranking,
        expRankNotation: String
    ) {
        // arrange
        val ranks = cards.split(SINGLE_SPACE).map { Rank.fromKeyOrThrow(it.first().toString()) }

        // act
        val actHandCombination = handCombinationService.findWorstPossibleHandCombination(
            ranking = expRanking,
            rankNotation = ranks.joinToString(separator = EMPTY_STRING) { it.key }
        )

        // assert
        with(actHandCombination) {
            shouldNotBeNull()
            ranking shouldBe expRanking
            hand shouldBe expRankNotation
        }
    }
}