package com.bigstackbully.rankmyhand.model.combination

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.InputStream

val ONE_PAIR_HANDS: Map<String, HandCombination> by lazy {
    val mapper = jacksonObjectMapper()
    val stream: InputStream = object {}.javaClass.getResourceAsStream("/one_pair_hands.json")
        ?: error("Resource not found: one_pair_hands.json")

    // Specify the type explicitly
    val type = object : com.fasterxml.jackson.core.type.TypeReference<Map<String, HandCombination>>() {}
    mapper.readValue(stream, type)
}