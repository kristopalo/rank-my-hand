package com.bigstackbully.rankmyhand.model.handstrengths

object RoyalFlushHandStrengths {
    val values = mapOf<String, HandStats>(
        "A K Q J T" to HandStats(key = "A K Q J T", relativeStrength = 1.0, absoluteStrength = 1.00),
        "K Q J T 9" to HandStats(key = "K Q J T 9", relativeStrength = 1.0, absoluteStrength = 0.99)
    )
}