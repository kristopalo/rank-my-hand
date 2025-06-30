package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.enums.CardRank

data class RankGroup(
    val ranks: List<CardRank>
) {
    val groupSize: Int = ranks.size
    val totalValue: Int = ranks.sumOf { it.value }
}
