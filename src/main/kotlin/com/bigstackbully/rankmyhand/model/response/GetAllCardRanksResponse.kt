package com.bigstackbully.rankmyhand.model.response

import com.bigstackbully.rankmyhand.model.dto.CardRankDto

data class GetAllCardRanksResponse(
    val cardRanks: List<CardRankDto>
)