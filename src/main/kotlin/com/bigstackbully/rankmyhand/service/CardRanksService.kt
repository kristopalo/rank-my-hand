package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.Ranking
import org.springframework.stereotype.Service

@Service
class CardRanksService {

    fun getAllCardRanks(): List<CardRank> {
        return CardRank.entries.filterNot { it == CardRank.LOW_ACE }
    }
}
