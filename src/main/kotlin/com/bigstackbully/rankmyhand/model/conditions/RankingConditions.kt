package com.bigstackbully.rankmyhand.model.conditions

import com.bigstackbully.rankmyhand.model.Hand
import com.bigstackbully.rankmyhand.model.enums.Rank

val hasFiveCards: (Hand) -> Boolean = { hand -> hand.cards.size == 5 }
val hasAtLeastFourCards: (Hand) -> Boolean = { hand -> hand.cards.size >= 4 }
val hasAtLeastThreeCards: (Hand) -> Boolean = { hand -> hand.cards.size >= 3 }
val hasAtLeastTwoCards: (Hand) -> Boolean = { hand -> hand.cards.size >= 2 }
val hasAtLeastOneCard: (Hand) -> Boolean = { hand -> hand.cards.isNotEmpty() }

val isSuited = { hand: Hand -> hand.isSuited }
val isStraight = { hand: Hand -> hand.areCardsInConsecutiveDescOrder }

val isAceHigh: (Hand) -> Boolean = { hand -> hand.highestRank == Rank.ACE }
val hasFourOfAKind: (Hand) -> Boolean = { hand -> hand.hasFourOfAKind }
val hasThreeOfAKind: (Hand) -> Boolean = { hand -> hand.hasThreeOfAKind }
val hasTwoPairs: (Hand) -> Boolean = { hand -> hand.hasTwoPairs }
val hasPair: (Hand) -> Boolean = { hand -> hand.hasPair }