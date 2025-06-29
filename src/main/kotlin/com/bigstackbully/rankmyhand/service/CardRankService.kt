package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.enums.CardRank
import org.springframework.stereotype.Service

@Service
class CardRankService {

    fun getAllCardRanks(): List<CardRank> = CardRank.entries
}
