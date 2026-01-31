package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.enums.Rank
import com.bigstackbully.rankmyhand.model.enums.Card
import org.springframework.stereotype.Service

@Service
class CardService {
    fun getAllCards(): List<Card> {
        return Card.entries.filterNot { it.rank == Rank.LOW_ACE }
    }
}
