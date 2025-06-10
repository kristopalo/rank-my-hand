package com.bigstackbully.rankmyhand.model.handstrengths

object RoyalFlushHandStrengths {
    val values = mapOf<String, HandStrength>(
        "A K Q J T" to HandStrength(key = "A K Q J T", rankStrength = 1.0, handStrength = 1.00),
        "K Q J T 9" to HandStrength(key = "K Q J T 9", rankStrength = 1.0, handStrength = 0.99)
    )
}