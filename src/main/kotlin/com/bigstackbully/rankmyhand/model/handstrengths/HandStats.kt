package com.bigstackbully.rankmyhand.model.handstrengths

data class HandStats(
//    val key: String,
//    val relativeStrength: Double, // relStrength <- TODO Kristo @ 10.06.2025 -> This can be calculated on the fly from the relativePosition
//    val absoluteStrength: Double, // absStrength <- TODO Kristo @ 10.06.2025 -> This can be calculated on the fly from the absolutePosition
    val relativePosition: Int, // relPos: Int
    val absolutePosition: Int //  absPos: Int
)