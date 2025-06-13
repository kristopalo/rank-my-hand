package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.RankUnit
import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.bigstackbully.rankmyhand.model.enums.HandRanking
import com.bigstackbully.rankmyhand.model.enums.RankUnitTemplate
import com.bigstackbully.rankmyhand.model.template.HandRankingTemplate
import com.bigstackbully.rankmyhand.model.template.HandTemplate
import org.springframework.stereotype.Service

@Service
class HandTemplateService {

    fun composeAllPossibleHandCombinations(): List<HandRankingTemplate> {
        // TODO Kristo @ 12.06.2025 -> Iterate the list of hand ranking templates and generate all possible hand combinations for each of them
        val handRankingTemplates = composeAllHandRankingTemplates()
        val cardRanks = CardRank.entries.toList()
            .filter { it.value > 1 }
            .sortedByDescending { it.value }

        val handTemplates = mutableListOf<HandTemplate>()

        for (hrt in handRankingTemplates) {

        }

        return handRankingTemplates
    }

    fun createAllPossibleHandCombinations(
        handRankingTemplate: HandRankingTemplate,
        cardRanks: List<CardRank>
    ): List<HandTemplate> {
        return createAllPossibleHandTemplates(
            rankUnitTemplates = handRankingTemplate.rankUnitTemplates,
            cardRanks = cardRanks,
            handTemplates = listOf())
    }

    fun createAllPossibleHandTemplates(
        rankUnitTemplates: List<RankUnitTemplate>,
        cardRanks: List<CardRank>,
        handTemplates: List<HandTemplate>
    ): List<HandTemplate> {
        if (ran)

        for (cardRank in cardRanks) {

        }

        return listOf()
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