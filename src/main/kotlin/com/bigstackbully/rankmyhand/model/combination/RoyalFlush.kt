package com.bigstackbully.rankmyhand.model.combination

import com.bigstackbully.rankmyhand.model.enums.HandRanking.ROYAL_FLUSH

fun composeRoyalFlushHands(): HashMap<String, HandCombination> = hashMapOf(
        "AKQJT" to HandCombination("AKQJT", ROYAL_FLUSH, 1, 1.000000, 1, 1.000000)
)
