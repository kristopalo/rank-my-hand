package com.bigstackbully.rankmyhand.model.response

import com.bigstackbully.rankmyhand.model.enums.HandRank

data class EvaluationResultResponse(
    val hand: String,
    val handInShortNotation: String,
    val rank: HandRank,
    val serializedValue: String
)