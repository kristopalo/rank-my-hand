package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.enums.Card.*
import com.bigstackbully.rankmyhand.model.request.EvaluationRequest
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class EvaluationRequestTransformerTest {

    private val transformer = EvaluationRequestTransformer()

    @Nested
    inner class SuccessfulTransformationTests {

        @Test
        fun `should transform request with space-separated cards`() {
            // arrange
            val request = EvaluationRequest(cards = "As Kh Qd Jc Ts")

            // act
            val command = transformer.toCommand(request)

            // assert
            with(command.evaluationContext) {
                holeCards shouldBe listOf(ACE_OF_SPADES, KING_OF_HEARTS)
                boardCards shouldBe listOf(QUEEN_OF_DIAMONDS, JACK_OF_CLUBS, TEN_OF_SPADES)
            }
        }

        @Test
        fun `should transform request without separators`() {
            // arrange
            val request = EvaluationRequest(cards = "AsKhQdJcTs")

            // act
            val command = transformer.toCommand(request)

            // assert
            with(command.evaluationContext) {
                holeCards shouldBe listOf(ACE_OF_SPADES, KING_OF_HEARTS)
                boardCards shouldBe listOf(QUEEN_OF_DIAMONDS, JACK_OF_CLUBS, TEN_OF_SPADES)
            }
        }

        @ParameterizedTest
        @ValueSource(strings = [
            "As-Kh-Qd-Jc-Ts",
            "As,Kh,Qd,Jc,Ts",
            "As;Kh;Qd;Jc;Ts",
            "As.Kh.Qd.Jc.Ts",
            "As|Kh|Qd|Jc|Ts"
        ])
        fun `should transform request with various allowed separators`(cards: String) {
            // arrange
            val request = EvaluationRequest(cards = cards)

            // act
            val command = transformer.toCommand(request)

            // assert
            with(command.evaluationContext) {
                holeCards shouldBe listOf(ACE_OF_SPADES, KING_OF_HEARTS)
                boardCards shouldBe listOf(QUEEN_OF_DIAMONDS, JACK_OF_CLUBS, TEN_OF_SPADES)
            }
        }


        @Test
        fun `should correctly split hole cards and board cards`() {
            // arrange - 7 cards (2 hole + 5 board)
            val request = EvaluationRequest(cards = "As Kh Qd Jc Ts 9h 8d")

            // act
            val command = transformer.toCommand(request)

            // assert
            with(command.evaluationContext) {
                holeCards shouldBe listOf(ACE_OF_SPADES, KING_OF_HEARTS)
                boardCards shouldBe listOf(
                    QUEEN_OF_DIAMONDS, JACK_OF_CLUBS, TEN_OF_SPADES, NINE_OF_HEARTS, EIGHT_OF_DIAMONDS
                )
            }
        }

        @Test
        fun `should handle single card input`() {
            // arrange
            val request = EvaluationRequest(cards = "As")

            // act
            val command = transformer.toCommand(request)

            // assert
            with(command.evaluationContext) {
                holeCards shouldBe listOf(ACE_OF_SPADES)
                boardCards shouldBe emptyList()
            }
        }

        @Test
        fun `should handle two cards as hole cards only`() {
            // arrange
            val request = EvaluationRequest(cards = "As Kh")

            // act
            val command = transformer.toCommand(request)

            // assert
            with(command.evaluationContext) {
                holeCards shouldBe listOf(ACE_OF_SPADES, KING_OF_HEARTS)
                boardCards shouldBe emptyList()
            }
        }

        @Test
        fun `should parse all card ranks correctly`() {
            // arrange - test all ranks: A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, 2
            val request = EvaluationRequest(cards = "As Ks Qs Js Ts 9s 8s 7s 6s 5s 4s 3s 2s")

            // act
            val command = transformer.toCommand(request)

            // assert
            val allCards = command.evaluationContext.holeCards + command.evaluationContext.boardCards
            allCards shouldBe listOf(
                ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, TEN_OF_SPADES,
                NINE_OF_SPADES, EIGHT_OF_SPADES, SEVEN_OF_SPADES, SIX_OF_SPADES, FIVE_OF_SPADES,
                FOUR_OF_SPADES, THREE_OF_SPADES, TWO_OF_SPADES
            )
        }

        @Test
        fun `should parse all suits correctly`() {
            // arrange
            val request = EvaluationRequest(cards = "As Ah Ad Ac")

            // act
            val command = transformer.toCommand(request)

            // assert
            val allCards = command.evaluationContext.holeCards + command.evaluationContext.boardCards
            allCards shouldBe listOf(ACE_OF_SPADES, ACE_OF_HEARTS, ACE_OF_DIAMONDS, ACE_OF_CLUBS)
        }
    }

    @Nested
    inner class InvalidInputTests {

        @Test
        fun `should throw exception for invalid characters`() {
            // arrange
            val request = EvaluationRequest(cards = "As@Kh#Qd")

            // act & assert
            val exception = assertThrows<IllegalArgumentException> {
                transformer.toCommand(request)
            }

            exception.message shouldBe "Input 'As@Kh#Qd' contains these invalid characters: ['@', '#']. Only alphanumeric characters, whitespace, and the following item separators are allowed: ['-', ',', ';', '.', '|']."
        }

        @Test
        fun `should throw exception for empty input after filtering`() {
            // arrange
            val request = EvaluationRequest(cards = "   ")

            // act & assert
            val exception = assertThrows<IllegalArgumentException> {
                transformer.toCommand(request)
            }

            exception.message!!.contains("Unable to parse any valid cards from the input string") shouldBe true
        }

        @Test
        fun `should throw exception for odd number of characters`() {
            // arrange
            val request = EvaluationRequest(cards = "AsK")

            // act & assert
            val exception = assertThrows<IllegalArgumentException> {
                transformer.toCommand(request)
            }

            exception.message shouldBe "Input string can only contain an equal number of characters, 2 for each card."
        }

        @Test
        fun `should throw exception for invalid card notation`() {
            // arrange
            val request = EvaluationRequest(cards = "As Xx Kh")

            // act & assert
            val exception = assertThrows<IllegalArgumentException> {
                transformer.toCommand(request)
            }

            exception.message shouldBe "Unable to parse the following items into cards: 'Xx'"
        }

        @Test
        fun `should throw exception for multiple invalid cards`() {
            // arrange
            val request = EvaluationRequest(cards = "Xx Yy Zz")

            // act & assert
            val exception = assertThrows<IllegalArgumentException> {
                transformer.toCommand(request)
            }

            exception.message shouldBe "Unable to parse the following items into cards: 'Xx', 'Yy', 'Zz'"
        }

        @Test
        fun `should throw exception for duplicate cards`() {
            // arrange
            val request = EvaluationRequest(cards = "As Kh As")

            // act & assert
            val exception = assertThrows<IllegalArgumentException> {
                transformer.toCommand(request)
            }

            exception.message shouldBe "All cards in the provided hand have to be unique."
        }

        @Test
        fun `should throw exception for invalid suit`() {
            // arrange
            val request = EvaluationRequest(cards = "Ax")

            // act & assert
            val exception = assertThrows<IllegalArgumentException> {
                transformer.toCommand(request)
            }

            exception.message shouldBe "Unable to parse the following items into cards: 'Ax'"
        }

        @Test
        fun `should throw exception for invalid rank`() {
            // arrange
            val request = EvaluationRequest(cards = "Xs")

            // act & assert
            val exception = assertThrows<IllegalArgumentException> {
                transformer.toCommand(request)
            }

            exception.message shouldBe "Unable to parse the following items into cards: 'Xs'"
        }

        @ParameterizedTest
        @ValueSource(strings = ["1s", "Bs", "Es", "0s"])
        fun `should throw exception for invalid rank values`(cards: String) {
            // arrange
            val request = EvaluationRequest(cards = cards)

            // act & assert
            assertThrows<IllegalArgumentException> {
                transformer.toCommand(request)
            }
        }
    }

    @Nested
    inner class EdgeCaseTests {

        @Test
        fun `should handle extra whitespace between cards`() {
            // arrange
            val request = EvaluationRequest(cards = "As    Kh     Qd")

            // act
            val command = transformer.toCommand(request)

            // assert
            with(command.evaluationContext) {
                holeCards shouldBe listOf(ACE_OF_SPADES, KING_OF_HEARTS)
                boardCards shouldBe listOf(QUEEN_OF_DIAMONDS)
            }
        }

        @Test
        fun `should handle leading and trailing whitespace`() {
            // arrange
            val request = EvaluationRequest(cards = "   As Kh Qd   ")

            // act
            val command = transformer.toCommand(request)

            // assert
            with(command.evaluationContext) {
                holeCards shouldBe listOf(ACE_OF_SPADES, KING_OF_HEARTS)
                boardCards shouldBe listOf(QUEEN_OF_DIAMONDS)
            }
        }

        @Test
        fun `should handle mixed separators`() {
            // arrange
            val request = EvaluationRequest(cards = "As-Kh,Qd;Jc.Ts|9h")

            // act
            val command = transformer.toCommand(request)

            // assert
            val allCards = command.evaluationContext.holeCards + command.evaluationContext.boardCards
            allCards shouldBe listOf(
                ACE_OF_SPADES, KING_OF_HEARTS, QUEEN_OF_DIAMONDS,
                JACK_OF_CLUBS, TEN_OF_SPADES, NINE_OF_HEARTS
            )
        }

        @ParameterizedTest
        @ValueSource(strings = [
            "as kh qd jc ts",
            "AS KH QD JC TS",
            "As Kh Qd Jc Ts"
        ])
        fun `should handle case insensitive input`(cards: String) {
            // arrange
            val request = EvaluationRequest(cards = cards)

            // act
            val command = transformer.toCommand(request)

            // assert
            with(command.evaluationContext) {
                holeCards shouldBe listOf(ACE_OF_SPADES, KING_OF_HEARTS)
                boardCards shouldBe listOf(QUEEN_OF_DIAMONDS, JACK_OF_CLUBS, TEN_OF_SPADES)
            }
        }
    }
}
