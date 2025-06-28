package com.bigstackbully.rankmyhand.model.response

import com.bigstackbully.rankmyhand.model.dto.RankingDto

data class GetAllRankingsResponse(
    val handRankings: List<RankingDto>
)
