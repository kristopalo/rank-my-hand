package com.bigstackbully.rankmyhand.model.response

import com.bigstackbully.rankmyhand.model.dto.HandRankingDto

data class GetAllRankingsResponse(
    val handRankings: List<HandRankingDto>
)
