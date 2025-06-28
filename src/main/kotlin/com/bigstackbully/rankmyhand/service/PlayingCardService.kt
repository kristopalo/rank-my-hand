package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import org.springframework.stereotype.Service

@Service
class PlayingCardService {

    fun getAllCards(): List<PlayingCard> {
        return PlayingCard.entries.filterNot { it.rank == CardRank.LOW_ACE }
    }
}
