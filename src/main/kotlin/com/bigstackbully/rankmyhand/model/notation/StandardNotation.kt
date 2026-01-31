package com.bigstackbully.rankmyhand.model.notation

import com.bigstackbully.rankmyhand.model.RankUnit
import com.bigstackbully.rankmyhand.model.enums.Rank
import com.bigstackbully.rankmyhand.model.enums.Card
import com.bigstackbully.rankmyhand.model.enums.Card.Companion.cardDefaultComparator
import com.bigstackbully.rankmyhand.model.characteristic.HasRanks
import com.bigstackbully.rankmyhand.model.characteristic.SuitAware
import com.bigstackbully.rankmyhand.service.utils.areSuited
import com.bigstackbully.rankmyhand.utils.SINGLE_SPACE
import java.util.SortedSet

data class StandardNotation(
    val cards: List<Card>
) : HasRanks, SuitAware {

    override val ranks: List<Rank> = cards.map { it.rank }
    override val isSuited: Boolean = cards.areSuited()

    override fun toString() = cards
        .sortedWith(cardDefaultComparator)
        .joinToString(separator = SINGLE_SPACE) { it.standardNotation }

    companion object {
        fun of(rankUnits: SortedSet<RankUnit>): StandardNotation {
            val cards = rankUnits.flatMap { it.cards }
            return StandardNotation(cards)
        }
    }
}
