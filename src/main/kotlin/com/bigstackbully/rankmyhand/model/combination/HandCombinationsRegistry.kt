package com.bigstackbully.rankmyhand.model.combination

import com.bigstackbully.rankmyhand.model.enums.CardRank
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.InputStream

val ROYAL_FLUSH_HANDS: Map<String, HandCombination> by lazy { loadHandCombinationsFromResource("royal_flush_hands.json") }
val STRAIGHT_FLUSH_HANDS: Map<String, HandCombination> by lazy { loadHandCombinationsFromResource("straight_flush_hands.json") }
val FOUR_OF_A_KIND_HANDS: Map<String, HandCombination> by lazy { loadHandCombinationsFromResource("four_of_a_kind_hands.json") }
val FULL_HOUSE_HANDS: Map<String, HandCombination> by lazy { loadHandCombinationsFromResource("full_house_hands.json") }
val FLUSH_HANDS: Map<String, HandCombination> by lazy { loadHandCombinationsFromResource("flush_hands.json") }
val STRAIGHT_HANDS: Map<String, HandCombination> by lazy { loadHandCombinationsFromResource("straight_hands.json") }
val THREE_OF_A_KIND_HANDS: Map<String, HandCombination> by lazy { loadHandCombinationsFromResource("three_of_a_kind_hands.json") }
val TWO_PAIR_HANDS: Map<String, HandCombination> by lazy { loadHandCombinationsFromResource("two_pair_hands.json") }
val ONE_PAIR_HANDS: Map<String, HandCombination> by lazy { loadHandCombinationsFromResource("one_pair_hands.json") }
val HIGH_CARD_HANDS: Map<String, HandCombination> by lazy { loadHandCombinationsFromResource("high_card_hands.json") }

val WHEEL_STRAIGHT_RANKS: Set<CardRank> = setOf(
    CardRank.FIVE,
    CardRank.FOUR,
    CardRank.THREE,
    CardRank.TWO,
    CardRank.ACE
)

val WHEEL_STRAIGHT_SHORT_NOTATION: String = WHEEL_STRAIGHT_RANKS.joinToString(separator = "") { it.shortNotation }

val WHEEL_STRAIGHT_SERIALIZED_VALUE: String = WHEEL_STRAIGHT_RANKS.map { rank -> if (rank == CardRank.ACE) CardRank.ACE_LOW else rank }
    .map { it.value }
    .joinToString(separator = "-")

fun loadHandCombinationsFromResource(resourceName: String): Map<String, HandCombination> {
    val mapper = jacksonObjectMapper()
    val stream: InputStream = object {}.javaClass.getResourceAsStream("/$resourceName")
        ?: error("Resource not found: $resourceName")
    val type = object : com.fasterxml.jackson.core.type.TypeReference<Map<String, HandCombination>>() {}
    return mapper.readValue(stream, type)
}