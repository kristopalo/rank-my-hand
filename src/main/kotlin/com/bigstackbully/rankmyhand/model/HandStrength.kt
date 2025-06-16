package com.bigstackbully.rankmyhand.model

import javax.swing.text.Position

data class HandStrength(
    val absolutePosition: Int,
    val absoluteStrength: Double,
    val relativePosition: Int,
    val relativeStrength: Double
)
