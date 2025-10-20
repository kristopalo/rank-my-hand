package com.bigstackbully.rankmyhand.model.notation

import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.RankUnit
import com.bigstackbully.rankmyhand.model.enums.Rank
import com.bigstackbully.rankmyhand.model.characteristic.HasRanks
import com.bigstackbully.rankmyhand.model.characteristic.SuitAware
import com.bigstackbully.rankmyhand.service.utils.areSuited
import com.bigstackbully.rankmyhand.service.utils.toRankNotation
import com.bigstackbully.rankmyhand.utils.OFF_SUIT
import com.bigstackbully.rankmyhand.utils.SUITED
import java.util.SortedSet

// TODO Kristo @ 20.10.2025 -> RankNotation, CocktailNotation / SignatureNotation and StandardNotation

data class SignatureNotation(
    override val ranks: List<Rank>,
    override val isSuited: Boolean = false
) : HasRanks, SuitAware {

    override fun toString() = "${ranks.toRankNotation()}${if (isSuited) SUITED else OFF_SUIT}"

    companion object {

        fun from(input: String) = {
            val ranks = input.map { char -> Rank.fromKeyOrThrow(char.toString()) }
            SignatureNotation(ranks)
        }

        fun from(hand: Hand) {
            val ranks = hand.cards.map { card -> card.rank }
            SignatureNotation(ranks)
        }

        fun of(rankUnits: SortedSet<RankUnit>): SignatureNotation {
            val rankNotation = rankUnits.toRankNotation()
            return SignatureNotation(ranks = rankNotation.ranks, isSuited = rankUnits.areSuited())
        }
    }
}