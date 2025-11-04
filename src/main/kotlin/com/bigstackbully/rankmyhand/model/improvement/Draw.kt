package com.bigstackbully.rankmyhand.model.improvement

import com.bigstackbully.rankmyhand.model.enums.DrawType

data class Draw(
    // TODO Kristo @ 02.11.2025 -> Add more properties here
    val drawType: DrawType,
    val numberOfOuts: Int
)