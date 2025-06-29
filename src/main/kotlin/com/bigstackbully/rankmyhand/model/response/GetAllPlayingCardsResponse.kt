package com.bigstackbully.rankmyhand.model.response

import com.bigstackbully.rankmyhand.model.dto.PlayingCardDto

data class GetAllPlayingCardsResponse(
    val playingCards: List<PlayingCardDto>
)
