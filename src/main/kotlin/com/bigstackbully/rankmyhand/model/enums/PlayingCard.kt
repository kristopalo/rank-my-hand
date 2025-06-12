package com.bigstackbully.rankmyhand.model.enums

enum class PlayingCard(
    val rank: CardRank,
    val suit: Suit
) {
    // https://en.wikipedia.org/wiki/Playing_cards_in_Unicode
    // https://www.baeldung.com/kotlin/enum

    TWO_OF_HEARTS(rank = CardRank.TWO, suit = Suit.HEARTS),
    THREE_OF_HEARTS(rank = CardRank.THREE, suit = Suit.HEARTS),
    FOUR_OF_HEARTS(rank = CardRank.FOUR, suit = Suit.HEARTS),
    FIVE_OF_HEARTS(rank = CardRank.FIVE, suit = Suit.HEARTS),
    SIX_OF_HEARTS(rank = CardRank.SIX, suit = Suit.HEARTS),
    SEVEN_OF_HEARTS(rank = CardRank.SEVEN, suit = Suit.HEARTS),
    EIGHT_OF_HEARTS(rank = CardRank.EIGHT, suit = Suit.HEARTS),
    NINE_OF_HEARTS(rank = CardRank.NINE, suit = Suit.HEARTS),
    TEN_OF_HEARTS(rank = CardRank.TEN, suit = Suit.HEARTS),
    JACK_OF_HEARTS(rank = CardRank.JACK, suit = Suit.HEARTS),
    QUEEN_OF_HEARTS(rank = CardRank.QUEEN, suit = Suit.HEARTS),
    KING_OF_HEARTS(rank = CardRank.KING, suit = Suit.HEARTS),
    ACE_OF_HEARTS(rank = CardRank.ACE, suit = Suit.HEARTS),

    TWO_OF_SPADES(rank = CardRank.TWO, suit = Suit.SPADES),
    THREE_OF_SPADES(rank = CardRank.THREE, suit = Suit.SPADES),
    FOUR_OF_SPADES(rank = CardRank.FOUR, suit = Suit.SPADES),
    FIVE_OF_SPADES(rank = CardRank.FIVE, suit = Suit.SPADES),
    SIX_OF_SPADES(rank = CardRank.SIX, suit = Suit.SPADES),
    SEVEN_OF_SPADES(rank = CardRank.SEVEN, suit = Suit.SPADES),
    EIGHT_OF_SPADES(rank = CardRank.EIGHT, suit = Suit.SPADES),
    NINE_OF_SPADES(rank = CardRank.NINE, suit = Suit.SPADES),
    TEN_OF_SPADES(rank = CardRank.TEN, suit = Suit.SPADES),
    JACK_OF_SPADES(rank = CardRank.JACK, suit = Suit.SPADES),
    QUEEN_OF_SPADES(rank = CardRank.QUEEN, suit = Suit.SPADES),
    KING_OF_SPADES(rank = CardRank.KING, suit = Suit.SPADES),
    ACE_OF_SPADES(rank = CardRank.ACE, suit = Suit.SPADES),

    TWO_OF_DIAMONDS(rank = CardRank.TWO, suit = Suit.DIAMONDS),
    THREE_OF_DIAMONDS(rank = CardRank.THREE, suit = Suit.DIAMONDS),
    FOUR_OF_DIAMONDS(rank = CardRank.FOUR, suit = Suit.DIAMONDS),
    FIVE_OF_DIAMONDS(rank = CardRank.FIVE, suit = Suit.DIAMONDS),
    SIX_OF_DIAMONDS(rank = CardRank.SIX, suit = Suit.DIAMONDS),
    SEVEN_OF_DIAMONDS(rank = CardRank.SEVEN, suit = Suit.DIAMONDS),
    EIGHT_OF_DIAMONDS(rank = CardRank.EIGHT, suit = Suit.DIAMONDS),
    NINE_OF_DIAMONDS(rank = CardRank.NINE, suit = Suit.DIAMONDS),
    TEN_OF_DIAMONDS(rank = CardRank.TEN, suit = Suit.DIAMONDS),
    JACK_OF_DIAMONDS(rank = CardRank.JACK, suit = Suit.DIAMONDS),
    QUEEN_OF_DIAMONDS(rank = CardRank.QUEEN, suit = Suit.DIAMONDS),
    KING_OF_DIAMONDS(rank = CardRank.KING, suit = Suit.DIAMONDS),
    ACE_OF_DIAMONDS(rank = CardRank.ACE, suit = Suit.DIAMONDS),

    TWO_OF_CLUBS(rank = CardRank.TWO, suit = Suit.CLUBS),
    THREE_OF_CLUBS(rank = CardRank.THREE, suit = Suit.CLUBS),
    FOUR_OF_CLUBS(rank = CardRank.FOUR, suit = Suit.CLUBS),
    FIVE_OF_CLUBS(rank = CardRank.FIVE, suit = Suit.CLUBS),
    SIX_OF_CLUBS(rank = CardRank.SIX, suit = Suit.CLUBS),
    SEVEN_OF_CLUBS(rank = CardRank.SEVEN, suit = Suit.CLUBS),
    EIGHT_OF_CLUBS(rank = CardRank.EIGHT, suit = Suit.CLUBS),
    NINE_OF_CLUBS(rank = CardRank.NINE, suit = Suit.CLUBS),
    TEN_OF_CLUBS(rank = CardRank.TEN, suit = Suit.CLUBS),
    JACK_OF_CLUBS(rank = CardRank.JACK, suit = Suit.CLUBS),
    QUEEN_OF_CLUBS(rank = CardRank.QUEEN, suit = Suit.CLUBS),
    KING_OF_CLUBS(rank = CardRank.KING, suit = Suit.CLUBS),
    ACE_OF_CLUBS(rank = CardRank.ACE, suit = Suit.CLUBS);

    val abbreviation: String = "${rank.shortNotation.uppercase()}${suit.abbreviation.lowercase()}"

    companion object {
        fun fromAbbreviation(abbreviation: String): PlayingCard? {
            return entries.find { it.abbreviation == abbreviation }
        }
    }
}

val playingCardComparator = compareByDescending<PlayingCard> { it.rank.value }
    .thenBy { it.suit.ordinal }