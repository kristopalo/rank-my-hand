package com.bigstackbully.rankmyhand.model.notation

import com.bigstackbully.rankmyhand.model.RankUnit
import com.bigstackbully.rankmyhand.model.enums.Rank
import com.bigstackbully.rankmyhand.model.enums.PlayingCard
import com.bigstackbully.rankmyhand.model.enums.PlayingCard.Companion.playingCardDefaultComparator
import com.bigstackbully.rankmyhand.model.characteristic.HasRanks
import com.bigstackbully.rankmyhand.model.characteristic.SuitAware
import com.bigstackbully.rankmyhand.service.utils.areSuited
import com.bigstackbully.rankmyhand.utils.SINGLE_SPACE
import java.util.SortedSet

data class StandardNotation(
    val cards: List<PlayingCard>
) : HasRanks, SuitAware {

    override val ranks: List<Rank> = cards.map { it.rank }

    override val isSuited: Boolean = cards.areSuited()

    override fun toString() = cards
        .sortedWith(playingCardDefaultComparator)
        .joinToString(separator = SINGLE_SPACE) { it.standardNotation }

    companion object {
        fun of(rankUnits: SortedSet<RankUnit>): StandardNotation {
            val cards = rankUnits.flatMap { it.cards }
            return StandardNotation(cards)
        }
    }
}
