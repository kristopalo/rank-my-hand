package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.HandEvaluationResult
import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.HandStrength
import com.bigstackbully.rankmyhand.model.command.EvaluateHandCommand
import com.bigstackbully.rankmyhand.model.enums.HandRanking.FULL_HOUSE
import com.bigstackbully.rankmyhand.model.enums.PlayingCard.*
import com.bigstackbully.rankmyhand.model.request.EvaluateHandRequest
import com.bigstackbully.rankmyhand.model.response.EvaluationResultResponse
import com.bigstackbully.rankmyhand.service.EvaluationRequestTransformer
import com.bigstackbully.rankmyhand.service.EvaluationResultTransformer
import com.bigstackbully.rankmyhand.service.EvaluatorService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyOrder
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@WebMvcTest(EvaluatorController::class)
class EvaluatorControllerTest : ShouldSpec({

    val evaluationRequestTransformer = mockk<EvaluationRequestTransformer>()
    val evaluatorService = mockk<EvaluatorService>()
    val evaluationResultTransformer = mockk<EvaluationResultTransformer>()

    val controller = EvaluatorController(
        evaluationRequestTransformer,
        evaluatorService,
        evaluationResultTransformer
    )

    val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    val objectMapper = jacksonObjectMapper()

    context("try to evaluate hand 'Ks Kh Kd Ac As'") {
        // arrange
        val request = EvaluateHandRequest(hand = "Ks Kh Kd Ac As")
        val command = EvaluateHandCommand(hand = Hand.of(
            cards = listOf(
                KING_OF_SPADES,
                KING_OF_HEARTS,
                KING_OF_DIAMONDS,
                ACE_OF_SPADES,
                ACE_OF_HEARTS
            )
        ))
        val evalResult = HandEvaluationResult(
            hand = "Ks Kh Kd As Ah",
            handRanking = FULL_HOUSE,
            serializedValue = "7-39-28",
            shortNotation = "KKKAA",
            handStrength = HandStrength(
                absolutePosition = 179,
                absoluteStrength = 0.976146,
                relativePosition = 13,
                relativeStrength = 0.923077
            )
        )

        val expectedResponse = EvaluationResultResponse(
            hand = "Ks Kh Kd Ac As",
            ranking = FULL_HOUSE,
            shortNotation = "KKKAA",
            serializedValue = "7-39-28",
            absolutePosition = 179,
            absoluteStrength = 0.976146,
            relativePosition = 13,
            relativeStrength = 0.923077
        )

        every { evaluationRequestTransformer.toCommand(request) } returns command
        every { evaluatorService.evaluate(command) } returns evalResult
        every { evaluationResultTransformer.toResponse(any()) } returns expectedResponse

        // act
        val response = mockMvc.post("/api/evaluator/evaluate-hand") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andReturn().response

        // assert
        response.status shouldBe 200
        val parsedBody: EvaluationResultResponse = objectMapper.readValue(response.contentAsString)
        parsedBody shouldBe expectedResponse

        verifyOrder {
            evaluationRequestTransformer.toCommand(request)
            evaluatorService.evaluate(command)
            evaluationResultTransformer.toResponse(evalResult)
        }
    }
})