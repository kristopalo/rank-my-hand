package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.enums.Ranking
import com.bigstackbully.rankmyhand.model.request.EvaluationRequest
import com.bigstackbully.rankmyhand.model.response.EvaluationResponse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class EvaluatorControllerIntegrationTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    private val objectMapper = jacksonObjectMapper()

    @Test
    fun `should evaluate a full house hand correctly`() {
        // arrange
        val evalReq = EvaluationRequest(cards = "As Ah Ad Ks Kh")

        // act
        val response = mockMvc.post("/api/evaluator/evaluate") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(evalReq)
        }.andReturn().response

        // assert
        response.status shouldBe HttpStatus.OK.value()

        val parsedBody: EvaluationResponse = objectMapper.readValue(response.contentAsString)
        parsedBody.hand shouldBe "As Ah Ad Ks Kh"
        parsedBody.ranking shouldBe Ranking.FULL_HOUSE
        parsedBody.shortNotation shouldBe "AAAKK"
    }

    @ParameterizedTest
    @CsvSource(
        "As Ks Qs Js Ts, ROYAL_FLUSH",
        "9h 8h 7h 6h 5h, STRAIGHT_FLUSH",
        "Kd Kh Ks Kc 2s, FOUR_OF_A_KIND",
        "Qc Qd Qh 7s 7d, FULL_HOUSE",
        "Ac 9c 7c 5c 3c, FLUSH",
        "Td 9c 8h 7s 6d, STRAIGHT",
        "Jh Jd Js 4c 2s, THREE_OF_A_KIND",
        "8s 8c 5h 5d Kc, TWO_PAIR",
        "6h 6d As Kc Qh, ONE_PAIR",
        "Kd Qc 9s 7h 4d, HIGH_CARD"
    )
    fun `should evaluate various hand rankings correctly`(cards: String, expectedRanking: String) {
        // arrange
        val evalReq = EvaluationRequest(cards = cards)

        // act
        val response = mockMvc.post("/api/evaluator/evaluate") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(evalReq)
        }.andReturn().response

        // assert
        response.status shouldBe HttpStatus.OK.value()

        val parsedBody: EvaluationResponse = objectMapper.readValue(response.contentAsString)
        parsedBody.ranking shouldBe Ranking.valueOf(expectedRanking)
    }

    @Test
    fun `should evaluate hole cards with board cards correctly`() {
        // arrange - simulating Texas Hold'em with hole cards (As Kh) and board (Qd Jc Ts 2h 3c)
        val evalReq = EvaluationRequest(cards = "As Kh Qd Jc Ts 2h 3c")

        // act
        val response = mockMvc.post("/api/evaluator/evaluate") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(evalReq)
        }.andReturn().response

        // assert
        response.status shouldBe HttpStatus.OK.value()

        val parsedBody: EvaluationResponse = objectMapper.readValue(response.contentAsString)
        parsedBody.ranking shouldBe Ranking.STRAIGHT
        parsedBody.hand shouldBe "As Kh Qd Jc Ts"
    }

    @Test
    fun `should return 400 when cards input is an invalid string`() {
        // arrange
        val evalReq = EvaluationRequest(cards = "! ? ## $$ % 1b Xy aaa")

        // act
        val response = mockMvc.post("/api/evaluator/evaluate") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(evalReq)
        }.andReturn().response

        // assert
        response.status shouldBe HttpStatus.BAD_REQUEST.value()
    }
}
