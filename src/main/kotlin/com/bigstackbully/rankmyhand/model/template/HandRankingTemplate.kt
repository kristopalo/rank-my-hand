package com.bigstackbully.rankmyhand.model.template

import com.bigstackbully.rankmyhand.model.enums.HandRanking
import com.bigstackbully.rankmyhand.model.enums.RankUnitTemplate

data class HandRankingTemplate(
    val ranking: HandRanking,
    val rankUnitTemplates: List<RankUnitTemplate>
)
