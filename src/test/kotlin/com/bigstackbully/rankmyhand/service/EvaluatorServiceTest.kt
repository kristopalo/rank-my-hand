package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.EvaluationContext
import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.HandStrength
import com.bigstackbully.rankmyhand.model.command.EvaluationCommand
import com.bigstackbully.rankmyhand.model.enums.Card
import com.bigstackbully.rankmyhand.model.enums.Card.*
import com.bigstackbully.rankmyhand.model.enums.Ranking
import com.bigstackbully.rankmyhand.model.enums.Ranking.*
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class EvaluatorServiceTest {

    private lateinit var rankingService: RankingService
    private lateinit var handStrengthService: HandStrengthService
    private lateinit var evaluatorService: EvaluatorService

    @BeforeEach
    fun setUp() {
        rankingService = mockk()
        handStrengthService = mockk()
        evaluatorService = EvaluatorService(rankingService, handStrengthService)
    }

    @Nested
    inner class EvaluateTests {

        @Test
        fun `should evaluate a full house hand correctly`() {
            // arrange
            val holeCards = listOf(ACE_OF_SPADES, ACE_OF_HEARTS)
            val boardCards = listOf(ACE_OF_DIAMONDS, KING_OF_SPADES, KING_OF_HEARTS)
            val evalCmd = EvaluationCommand(
                evaluationContext = EvaluationContext(holeCards = holeCards, boardCards = boardCards)
            )
            val expectedHand = Hand.of(holeCards + boardCards)
            val expectedHandStrength = HandStrength(
                absolutePosition = 167,
                absoluteStrength = 0.977754,
                relativePosition = 1,
                relativeStrength = 1.0
            )

            every { rankingService.evaluateRanking(any()) } returns FULL_HOUSE
            every { handStrengthService.calculateHandStrength(FULL_HOUSE, any()) } returns expectedHandStrength

            // act
            val result = evaluatorService.evaluate(evalCmd)

            // assert
            result.ranking shouldBe FULL_HOUSE
            result.hand shouldBe expectedHand.standardNotation
            result.shortNotation shouldBe expectedHand.rankNotation
            result.handStrength shouldBe expectedHandStrength

            verify { rankingService.evaluateRanking(any()) }
            verify { handStrengthService.calculateHandStrength(FULL_HOUSE, any()) }
        }

        @Test
        fun `should evaluate a royal flush hand correctly`() {
            // arrange
            val cards = listOf(ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, TEN_OF_SPADES)
            val evalCmd = EvaluationCommand(
                evaluationContext = EvaluationContext(holeCards = cards.take(2), boardCards = cards.drop(2))
            )
            val expectedHand = Hand.of(cards)
            val expectedHandStrength = HandStrength(
                absolutePosition = 1,
                absoluteStrength = 1.0,
                relativePosition = 1,
                relativeStrength = 1.0
            )

            every { rankingService.evaluateRanking(any()) } returns ROYAL_FLUSH
            every { handStrengthService.calculateHandStrength(ROYAL_FLUSH, any()) } returns expectedHandStrength

            // act
            val result = evaluatorService.evaluate(evalCmd)

            // assert
            result.ranking shouldBe ROYAL_FLUSH
            result.hand shouldBe expectedHand.standardNotation
            result.handStrength shouldBe expectedHandStrength
        }

        @Test
        fun `should find best hand from 7 cards`() {
            // arrange - 7 cards where the best hand is a flush
            val holeCards = listOf(ACE_OF_HEARTS, KING_OF_HEARTS)
            val boardCards = listOf(QUEEN_OF_HEARTS, JACK_OF_HEARTS, TEN_OF_HEARTS, TWO_OF_SPADES, THREE_OF_CLUBS)
            val evalCmd = EvaluationCommand(
                evaluationContext = EvaluationContext(holeCards = holeCards, boardCards = boardCards)
            )

            val royalFlushStrength = HandStrength(1, 1.0, 1, 1.0)
            val weakerStrength = HandStrength(1000, 0.5, 100, 0.5)

            // Royal flush hand (the best possible)
            val royalFlushHand = Hand.of(listOf(ACE_OF_HEARTS, KING_OF_HEARTS, QUEEN_OF_HEARTS, JACK_OF_HEARTS, TEN_OF_HEARTS))

            every { rankingService.evaluateRanking(any()) } answers {
                val hand = firstArg<Hand>()
                if (hand == royalFlushHand) ROYAL_FLUSH else HIGH_CARD
            }
            every { handStrengthService.calculateHandStrength(ROYAL_FLUSH, any()) } returns royalFlushStrength
            every { handStrengthService.calculateHandStrength(HIGH_CARD, any()) } returns weakerStrength

            // act
            val result = evaluatorService.evaluate(evalCmd)

            // assert
            result.ranking shouldBe ROYAL_FLUSH
            result.handStrength.absoluteStrength shouldBe 1.0
        }

        @Test
        fun `should handle less than 5 cards`() {
            // arrange - only 3 cards
            val holeCards = listOf(ACE_OF_SPADES, KING_OF_SPADES)
            val boardCards = listOf(QUEEN_OF_SPADES)
            val evalCmd = EvaluationCommand(
                evaluationContext = EvaluationContext(holeCards = holeCards, boardCards = boardCards)
            )
            val expectedHandStrength = HandStrength(7000, 0.1, 1000, 0.1)

            every { rankingService.evaluateRanking(any()) } returns HIGH_CARD
            every { handStrengthService.calculateHandStrength(HIGH_CARD, any()) } returns expectedHandStrength

            // act
            val result = evaluatorService.evaluate(evalCmd)

            // assert
            result.ranking shouldBe HIGH_CARD
            result.handStrength shouldBe expectedHandStrength
        }

        @Test
        fun `should generate correct serialized value`() {
            // arrange
            val cards = listOf(ACE_OF_SPADES, ACE_OF_HEARTS, ACE_OF_DIAMONDS, KING_OF_SPADES, KING_OF_HEARTS)
            val evalCmd = EvaluationCommand(
                evaluationContext = EvaluationContext(holeCards = cards.take(2), boardCards = cards.drop(2))
            )
            val expectedHandStrength = HandStrength(167, 0.977754, 1, 1.0)

            every { rankingService.evaluateRanking(any()) } returns FULL_HOUSE
            every { handStrengthService.calculateHandStrength(FULL_HOUSE, any()) } returns expectedHandStrength

            // act
            val result = evaluatorService.evaluate(evalCmd)

            // assert
            // Serialized value should be: ranking.strength-hand.serializedValue
            result.serializedValue shouldBe "7-${Hand.of(cards).serializedValue}"
        }
    }

    @Nested
    inner class AllHandRankingsTests {

        @ParameterizedTest
        @MethodSource("com.bigstackbully.rankmyhand.service.EvaluatorServiceTest#handRankingTestCases")
        fun `should correctly evaluate different hand rankings`(
            cards: List<Card>,
            expectedRanking: Ranking
        ) {
            // arrange
            val evalCmd = EvaluationCommand(
                evaluationContext = EvaluationContext(holeCards = cards.take(2), boardCards = cards.drop(2))
            )
            val handStrength = HandStrength(1, 1.0, 1, 1.0)

            every { rankingService.evaluateRanking(any()) } returns expectedRanking
            every { handStrengthService.calculateHandStrength(expectedRanking, any()) } returns handStrength

            // act
            val result = evaluatorService.evaluate(evalCmd)

            // assert
            result.ranking shouldBe expectedRanking
        }
    }

    companion object {
        @JvmStatic
        fun handRankingTestCases(): Stream<Arguments> = Stream.of(
            Arguments.of(
                listOf(ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, TEN_OF_SPADES),
                ROYAL_FLUSH
            ),
            Arguments.of(
                listOf(NINE_OF_HEARTS, EIGHT_OF_HEARTS, SEVEN_OF_HEARTS, SIX_OF_HEARTS, FIVE_OF_HEARTS),
                STRAIGHT_FLUSH
            ),
            Arguments.of(
                listOf(KING_OF_DIAMONDS, KING_OF_HEARTS, KING_OF_SPADES, KING_OF_CLUBS, TWO_OF_SPADES),
                FOUR_OF_A_KIND
            ),
            Arguments.of(
                listOf(QUEEN_OF_CLUBS, QUEEN_OF_DIAMONDS, QUEEN_OF_HEARTS, SEVEN_OF_SPADES, SEVEN_OF_DIAMONDS),
                FULL_HOUSE
            ),
            Arguments.of(
                listOf(ACE_OF_CLUBS, NINE_OF_CLUBS, SEVEN_OF_CLUBS, FIVE_OF_CLUBS, THREE_OF_CLUBS),
                FLUSH
            ),
            Arguments.of(
                listOf(TEN_OF_DIAMONDS, NINE_OF_CLUBS, EIGHT_OF_HEARTS, SEVEN_OF_SPADES, SIX_OF_DIAMONDS),
                STRAIGHT
            ),
            Arguments.of(
                listOf(JACK_OF_HEARTS, JACK_OF_DIAMONDS, JACK_OF_SPADES, FOUR_OF_CLUBS, TWO_OF_SPADES),
                THREE_OF_A_KIND
            ),
            Arguments.of(
                listOf(EIGHT_OF_SPADES, EIGHT_OF_CLUBS, FIVE_OF_HEARTS, FIVE_OF_DIAMONDS, KING_OF_CLUBS),
                TWO_PAIR
            ),
            Arguments.of(
                listOf(SIX_OF_HEARTS, SIX_OF_DIAMONDS, ACE_OF_SPADES, KING_OF_CLUBS, QUEEN_OF_HEARTS),
                ONE_PAIR
            ),
            Arguments.of(
                listOf(KING_OF_DIAMONDS, QUEEN_OF_CLUBS, NINE_OF_SPADES, SEVEN_OF_HEARTS, FOUR_OF_DIAMONDS),
                HIGH_CARD
            )
        )
    }
}
