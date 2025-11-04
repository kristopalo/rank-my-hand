package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.HandContext
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import org.junit.jupiter.api.Test

class DrawServiceTest {

    private val rankingService: RankingService = RankingService()
    private val handCombinationService: HandCombinationService = HandCombinationService(rankingService = rankingService)
    private val drawService: DrawService = DrawService(handCombinationService = handCombinationService)

    @Test
    fun `should evaluate to a straight draw`() {
        // arrange
        val handContext = HandContext(
            holeCards = listOf(
                PlayingCard.NINE_OF_HEARTS,
                PlayingCard.SIX_OF_SPADES
            ),
            boardCards = listOf(
                PlayingCard.TEN_OF_SPADES,
                PlayingCard.SIX_OF_SPADES,
                PlayingCard.KING_OF_CLUBS
            )
        )
        
        // act
        val actStraightDrawEvalResults = drawService.tryFindAllPossibleStraightDraws(handContext)
        
        // assert
        actStraightDrawEvalResults.shouldNotBeNull()
        actStraightDrawEvalResults.shouldNotBeEmpty()
    }
}