package com.bigstackbully.rankmyhand.model.combination

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.InputStream

val HIGH_CARD_HANDS: Map<String, HandCombination> by lazy {
    val mapper = jacksonObjectMapper()
    val stream: InputStream = object {}.javaClass.getResourceAsStream("/high_card_hands.json")
        ?: error("Resource not found: high_card_hands.json")
    val type = object : com.fasterxml.jackson.core.type.TypeReference<Map<String, HandCombination>>() {}
    mapper.readValue(stream, type)
}
