package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.enums.Rank
import org.springframework.stereotype.Service

@Service
class CardRankService {

    fun getAllCardRanks(): List<Rank> = Rank.entries
}
