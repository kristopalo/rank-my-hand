package com.bigstackbully.rankmyhand.model

import com.bigstackbully.rankmyhand.model.enums.Rank

data class RankGroup(
    val ranks: List<Rank>
) {
    val groupSize: Int = ranks.size
    val totalValue: Int = ranks.sumOf { it.value }
}
