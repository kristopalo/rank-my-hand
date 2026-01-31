package com.bigstackbully.rankmyhand.model.response

import com.bigstackbully.rankmyhand.model.dto.CardDto

data class GetAllCardsResponse(
    val cards: List<CardDto>
)
