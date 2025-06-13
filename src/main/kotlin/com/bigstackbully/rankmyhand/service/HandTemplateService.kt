package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.enums.HandRanking
import com.bigstackbully.rankmyhand.model.enums.RankUnitTemplate
import com.bigstackbully.rankmyhand.model.template.HandRankingTemplate
import org.springframework.stereotype.Service

@Service
class HandTemplateService {

    fun composeAllPossibleHandCombinations(): List<HandRankingTemplate> {
        val handRankingTemplates = mutableListOf<HandRankingTemplate>()

        // TODO Kristo @ 12.06.2025 -> Iterate the list of hand ranking templates and generate all possible hand combinations for each of them

        return handRankingTemplates
    }

    fun composeAllHandRankingTemplates(): List<HandRankingTemplate> = listOf(
        HandRankingTemplate(
            ranking = HandRanking.ROYAL_FLUSH,
            rankUnitTemplates = listOf(
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE
            )
        ),
        HandRankingTemplate(
            ranking = HandRanking.STRAIGHT_FLUSH,
            rankUnitTemplates = listOf(
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE
            )
        ),
        HandRankingTemplate(
            ranking = HandRanking.FOUR_OF_A_KIND,
            rankUnitTemplates = listOf(
                RankUnitTemplate.FOUR_OF_A_KIND,
                RankUnitTemplate.SINGLE
            )
        ),
        HandRankingTemplate(
            ranking = HandRanking.FULL_HOUSE,
            rankUnitTemplates = listOf(
                RankUnitTemplate.THREE_OF_A_KIND,
                RankUnitTemplate.PAIR
            )
        ),
        HandRankingTemplate(
            ranking = HandRanking.FLUSH,
            rankUnitTemplates = listOf(
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE
            )
        ),
        HandRankingTemplate(
            ranking = HandRanking.STRAIGHT,
            rankUnitTemplates = listOf(
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE
            )
        ),
        HandRankingTemplate(
            ranking = HandRanking.THREE_OF_A_KIND,
            rankUnitTemplates = listOf(
                RankUnitTemplate.THREE_OF_A_KIND,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE
            )
        ),
        HandRankingTemplate(
            ranking = HandRanking.TWO_PAIR,
            rankUnitTemplates = listOf(
                RankUnitTemplate.PAIR,
                RankUnitTemplate.PAIR,
                RankUnitTemplate.SINGLE
            )
        ),
        HandRankingTemplate(
            ranking = HandRanking.ONE_PAIR,
            rankUnitTemplates = listOf(
                RankUnitTemplate.PAIR,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE
            )
        ),
        HandRankingTemplate(
            ranking = HandRanking.HIGH_CARD,
            rankUnitTemplates = listOf(
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE,
                RankUnitTemplate.SINGLE
            )
        )
    )

}