package com.bigstackbully.rankmyhand.model.response

import com.bigstackbully.rankmyhand.model.enums.HandRanking

data class EvaluationResultResponse(
    val hand: String,
    val handInShortNotation: String,
    val rank: HandRanking,
    val serializedValue: String
)