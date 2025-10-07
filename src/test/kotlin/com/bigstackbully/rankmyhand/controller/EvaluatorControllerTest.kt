package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.response.EvaluationResponse
import com.bigstackbully.rankmyhand.service.EvaluationRequestTransformer
import com.bigstackbully.rankmyhand.service.EvaluationResultTransformer
import com.bigstackbully.rankmyhand.service.EvaluatorService
import com.bigstackbully.rankmyhand.testdata.composeHandEvaluationContextForFullHouse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyOrder
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
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
        val evalContext = composeHandEvaluationContextForFullHouse()
        val evalReq = evalContext.request
        val evalCmd = evalContext.command
        val evalResult = evalContext.result
        val expResponse = evalContext.response

        every { evaluationRequestTransformer.toCommand(evalReq) } returns evalCmd
        every { evaluatorService.evaluate(evalCmd) } returns evalResult
        every { evaluationResultTransformer.toResponse(any()) } returns expResponse

        // act
        val response = mockMvc.post("/api/evaluator/evaluate") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(evalReq)
        }.andReturn().response

        // assert
        response.status shouldBe HttpStatus.OK.value()

        val parsedBody: EvaluationResponse = objectMapper.readValue(response.contentAsString)
        parsedBody shouldBe expResponse

        verifyOrder {
            evaluationRequestTransformer.toCommand(evalReq)
            evaluatorService.evaluate(evalCmd)
            evaluationResultTransformer.toResponse(evalResult)
        }
    }
})