package com.bigstackbully.rankmyhand.domain.model.enums

enum class PlayingCard(
    val rank: CardRank,
    val suit: Suit
) {
    // https://en.wikipedia.org/wiki/Playing_cards_in_Unicode
    // https://www.baeldung.com/kotlin/enum

    TWO_OF_HEARTS(CardRank.TWO, Suit.HEARTS),
    THREE_OF_HEARTS(CardRank.THREE, Suit.HEARTS),
    FOUR_OF_HEARTS(CardRank.FOUR, Suit.HEARTS),
    FIVE_OF_HEARTS(CardRank.FIVE, Suit.HEARTS),
    SIX_OF_HEARTS(CardRank.SIX, Suit.HEARTS),
    SEVEN_OF_HEARTS(CardRank.SEVEN, Suit.HEARTS),
    EIGHT_OF_HEARTS(CardRank.EIGHT, Suit.HEARTS),
    NINE_OF_HEARTS(CardRank.NINE, Suit.HEARTS),
    TEN_OF_HEARTS(CardRank.TEN, Suit.HEARTS),
    JACK_OF_HEARTS(CardRank.JACK, Suit.HEARTS),
    QUEEN_OF_HEARTS(CardRank.QUEEN, Suit.HEARTS),
    KING_OF_HEARTS(CardRank.KING, Suit.HEARTS),
    ACE_OF_HEARTS(CardRank.ACE, Suit.HEARTS),

    TWO_OF_DIAMONDS(CardRank.TWO, Suit.DIAMONDS),
    THREE_OF_DIAMONDS(CardRank.THREE, Suit.DIAMONDS),
    FOUR_OF_DIAMONDS(CardRank.FOUR, Suit.DIAMONDS),
    FIVE_OF_DIAMONDS(CardRank.FIVE, Suit.DIAMONDS),
    SIX_OF_DIAMONDS(CardRank.SIX, Suit.DIAMONDS),
    SEVEN_OF_DIAMONDS(CardRank.SEVEN, Suit.DIAMONDS),
    EIGHT_OF_DIAMONDS(CardRank.EIGHT, Suit.DIAMONDS),
    NINE_OF_DIAMONDS(CardRank.NINE, Suit.DIAMONDS),
    TEN_OF_DIAMONDS(CardRank.TEN, Suit.DIAMONDS),
    JACK_OF_DIAMONDS(CardRank.JACK, Suit.DIAMONDS),
    QUEEN_OF_DIAMONDS(CardRank.QUEEN, Suit.DIAMONDS),
    KING_OF_DIAMONDS(CardRank.KING, Suit.DIAMONDS),
    ACE_OF_DIAMONDS(CardRank.ACE, Suit.DIAMONDS),

    TWO_OF_CLUBS(CardRank.TWO, Suit.CLUBS),
    THREE_OF_CLUBS(CardRank.THREE, Suit.CLUBS),
    FOUR_OF_CLUBS(CardRank.FOUR, Suit.CLUBS),
    FIVE_OF_CLUBS(CardRank.FIVE, Suit.CLUBS),
    SIX_OF_CLUBS(CardRank.SIX, Suit.CLUBS),
    SEVEN_OF_CLUBS(CardRank.SEVEN, Suit.CLUBS),
    EIGHT_OF_CLUBS(CardRank.EIGHT, Suit.CLUBS),
    NINE_OF_CLUBS(CardRank.NINE, Suit.CLUBS),
    TEN_OF_CLUBS(CardRank.TEN, Suit.CLUBS),
    JACK_OF_CLUBS(CardRank.JACK, Suit.CLUBS),
    QUEEN_OF_CLUBS(CardRank.QUEEN, Suit.CLUBS),
    KING_OF_CLUBS(CardRank.KING, Suit.CLUBS),
    ACE_OF_CLUBS(CardRank.ACE, Suit.CLUBS),

    TWO_OF_SPADES(CardRank.TWO, Suit.SPADES),
    THREE_OF_SPADES(CardRank.THREE, Suit.SPADES),
    FOUR_OF_SPADES(CardRank.FOUR, Suit.SPADES),
    FIVE_OF_SPADES(CardRank.FIVE, Suit.SPADES),
    SIX_OF_SPADES(CardRank.SIX, Suit.SPADES),
    SEVEN_OF_SPADES(CardRank.SEVEN, Suit.SPADES),
    EIGHT_OF_SPADES(CardRank.EIGHT, Suit.SPADES),
    NINE_OF_SPADES(CardRank.NINE, Suit.SPADES),
    TEN_OF_SPADES(CardRank.TEN, Suit.SPADES),
    JACK_OF_SPADES(CardRank.JACK, Suit.SPADES),
    QUEEN_OF_SPADES(CardRank.QUEEN, Suit.SPADES),
    KING_OF_SPADES(CardRank.KING, Suit.SPADES),
    ACE_OF_SPADES(CardRank.ACE, Suit.SPADES);

    val standardNotation: String
        get() = "${rank.name.first().uppercase()}${suit.name.first().lowercase()}"
}