package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.enums.Card
import com.bigstackbully.rankmyhand.model.enums.Rank
import org.springframework.stereotype.Service

@Service
class CardService {
    fun getAllCards(): List<Card> {
        return Card.entries.filterNot { it.rank == Rank.LOW_ACE }
    }
}
