package com.bigstackbully.rankmyhand.model.notation

import com.bigstackbully.rankmyhand.model.RankUnit
import com.bigstackbully.rankmyhand.model.characteristic.HasRanks
import com.bigstackbully.rankmyhand.model.enums.Rank
import com.bigstackbully.rankmyhand.service.utils.areWheelStraight
import com.bigstackbully.rankmyhand.service.utils.ranks
import com.bigstackbully.rankmyhand.service.utils.toRankNotation
import java.util.SortedSet

data class RankNotation(
    override val ranks: List<Rank>
) : HasRanks {

    override fun toString() = ranks.toRankNotation()

    companion object {

        fun from(rankUnits: SortedSet<RankUnit>): RankNotation {
            val ranks = rankUnits.ranks()
            return of(ranks)
        }

        fun from(input: String): RankNotation {
            val ranks = input.map { char -> Rank.fromKeyOrThrow(char.toString()) }
            return of(ranks)
        }

        fun of(ranks: List<Rank>): RankNotation {
            val normalizedRanks = if (ranks.areWheelStraight()) {
                ranks.map { rank ->
                    when (rank) {
                        Rank.ACE -> Rank.LOW_ACE
                        else -> rank
                    }
                }
            } else {
                ranks
            }

            return RankNotation(normalizedRanks)
        }

    }
}