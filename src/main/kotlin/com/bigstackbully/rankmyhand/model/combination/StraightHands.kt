package com.bigstackbully.rankmyhand.model.combination

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.InputStream

val STRAIGHT_HANDS: Map<String, HandCombination> by lazy {
    val mapper = jacksonObjectMapper()
    val stream: InputStream = object {}.javaClass.getResourceAsStream("/straight_hands.json")
        ?: error("Resource not found: straight_hands.json")
    val type = object : com.fasterxml.jackson.core.type.TypeReference<Map<String, HandCombination>>() {}
    mapper.readValue(stream, type)
}
