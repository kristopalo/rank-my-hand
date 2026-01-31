package com.bigstackbully.rankmyhand.model.enums

import com.bigstackbully.rankmyhand.utils.SINGLE_SPACE
import com.bigstackbully.rankmyhand.utils.UNDERSCORE

enum class Card(
    val rank: Rank,
    val suit: Suit
) : Comparable<Card> {
    LOW_ACE_OF_SPADES(rank = Rank.LOW_ACE, Suit.SPADES),
    TWO_OF_SPADES(rank = Rank.TWO, suit = Suit.SPADES),
    THREE_OF_SPADES(rank = Rank.THREE, suit = Suit.SPADES),
    FOUR_OF_SPADES(rank = Rank.FOUR, suit = Suit.SPADES),
    FIVE_OF_SPADES(rank = Rank.FIVE, suit = Suit.SPADES),
    SIX_OF_SPADES(rank = Rank.SIX, suit = Suit.SPADES),
    SEVEN_OF_SPADES(rank = Rank.SEVEN, suit = Suit.SPADES),
    EIGHT_OF_SPADES(rank = Rank.EIGHT, suit = Suit.SPADES),
    NINE_OF_SPADES(rank = Rank.NINE, suit = Suit.SPADES),
    TEN_OF_SPADES(rank = Rank.TEN, suit = Suit.SPADES),
    JACK_OF_SPADES(rank = Rank.JACK, suit = Suit.SPADES),
    QUEEN_OF_SPADES(rank = Rank.QUEEN, suit = Suit.SPADES),
    KING_OF_SPADES(rank = Rank.KING, suit = Suit.SPADES),
    ACE_OF_SPADES(rank = Rank.ACE, suit = Suit.SPADES),

    LOW_ACE_OF_HEARTS(rank = Rank.LOW_ACE, Suit.HEARTS),
    TWO_OF_HEARTS(rank = Rank.TWO, suit = Suit.HEARTS),
    THREE_OF_HEARTS(rank = Rank.THREE, suit = Suit.HEARTS),
    FOUR_OF_HEARTS(rank = Rank.FOUR, suit = Suit.HEARTS),
    FIVE_OF_HEARTS(rank = Rank.FIVE, suit = Suit.HEARTS),
    SIX_OF_HEARTS(rank = Rank.SIX, suit = Suit.HEARTS),
    SEVEN_OF_HEARTS(rank = Rank.SEVEN, suit = Suit.HEARTS),
    EIGHT_OF_HEARTS(rank = Rank.EIGHT, suit = Suit.HEARTS),
    NINE_OF_HEARTS(rank = Rank.NINE, suit = Suit.HEARTS),
    TEN_OF_HEARTS(rank = Rank.TEN, suit = Suit.HEARTS),
    JACK_OF_HEARTS(rank = Rank.JACK, suit = Suit.HEARTS),
    QUEEN_OF_HEARTS(rank = Rank.QUEEN, suit = Suit.HEARTS),
    KING_OF_HEARTS(rank = Rank.KING, suit = Suit.HEARTS),
    ACE_OF_HEARTS(rank = Rank.ACE, suit = Suit.HEARTS),

    LOW_ACE_OF_DIAMONDS(rank = Rank.LOW_ACE, Suit.DIAMONDS),
    TWO_OF_DIAMONDS(rank = Rank.TWO, suit = Suit.DIAMONDS),
    THREE_OF_DIAMONDS(rank = Rank.THREE, suit = Suit.DIAMONDS),
    FOUR_OF_DIAMONDS(rank = Rank.FOUR, suit = Suit.DIAMONDS),
    FIVE_OF_DIAMONDS(rank = Rank.FIVE, suit = Suit.DIAMONDS),
    SIX_OF_DIAMONDS(rank = Rank.SIX, suit = Suit.DIAMONDS),
    SEVEN_OF_DIAMONDS(rank = Rank.SEVEN, suit = Suit.DIAMONDS),
    EIGHT_OF_DIAMONDS(rank = Rank.EIGHT, suit = Suit.DIAMONDS),
    NINE_OF_DIAMONDS(rank = Rank.NINE, suit = Suit.DIAMONDS),
    TEN_OF_DIAMONDS(rank = Rank.TEN, suit = Suit.DIAMONDS),
    JACK_OF_DIAMONDS(rank = Rank.JACK, suit = Suit.DIAMONDS),
    QUEEN_OF_DIAMONDS(rank = Rank.QUEEN, suit = Suit.DIAMONDS),
    KING_OF_DIAMONDS(rank = Rank.KING, suit = Suit.DIAMONDS),
    ACE_OF_DIAMONDS(rank = Rank.ACE, suit = Suit.DIAMONDS),

    LOW_ACE_OF_CLUBS(rank = Rank.LOW_ACE, Suit.CLUBS),
    TWO_OF_CLUBS(rank = Rank.TWO, suit = Suit.CLUBS),
    THREE_OF_CLUBS(rank = Rank.THREE, suit = Suit.CLUBS),
    FOUR_OF_CLUBS(rank = Rank.FOUR, suit = Suit.CLUBS),
    FIVE_OF_CLUBS(rank = Rank.FIVE, suit = Suit.CLUBS),
    SIX_OF_CLUBS(rank = Rank.SIX, suit = Suit.CLUBS),
    SEVEN_OF_CLUBS(rank = Rank.SEVEN, suit = Suit.CLUBS),
    EIGHT_OF_CLUBS(rank = Rank.EIGHT, suit = Suit.CLUBS),
    NINE_OF_CLUBS(rank = Rank.NINE, suit = Suit.CLUBS),
    TEN_OF_CLUBS(rank = Rank.TEN, suit = Suit.CLUBS),
    JACK_OF_CLUBS(rank = Rank.JACK, suit = Suit.CLUBS),
    QUEEN_OF_CLUBS(rank = Rank.QUEEN, suit = Suit.CLUBS),
    KING_OF_CLUBS(rank = Rank.KING, suit = Suit.CLUBS),
    ACE_OF_CLUBS(rank = Rank.ACE, suit = Suit.CLUBS);

    val standardNotation: String = "${rank.key.uppercase()}${suit.standardNotation.lowercase()}"
    val key: String = standardNotation.uppercase()

    val displayName: String = name
        .split(UNDERSCORE)
        .joinToString(SINGLE_SPACE) { word ->
            if (word == "OF") "of"
            else word.lowercase().replaceFirstChar { it.uppercase() }
        }

    companion object {
        fun fromShortNotation(standardNotation: String): Card? {
            return entries
                .filterNot { it.rank == Rank.LOW_ACE }
                .find { it.standardNotation == standardNotation }
        }

        val cardDefaultComparator: Comparator<Card> =
            compareByDescending<Card> { it.rank.value }
                .thenByDescending { it.suit.ordinal }
    }
}