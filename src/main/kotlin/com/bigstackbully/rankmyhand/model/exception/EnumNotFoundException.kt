package com.bigstackbully.rankmyhand.model.exception

import com.bigstackbully.rankmyhand.model.enums.interfaces.KeyedEnum
import java.lang.IllegalArgumentException
import kotlin.reflect.KClass

const val ITEM_SEPARATOR = ", "

class EnumNotFoundException(
    identifier: String,
    enumClass: KClass<out Enum<*>>
) : IllegalArgumentException(
    buildString {
        append("Invalid identifier '$identifier'.")
        append(" ")
        append("No matching value found in enum class '${enumClass.simpleName}'.")
        append(" ")

        // Get enum entries using reflection
        val entries = enumClass.java.enumConstants?.toList().orEmpty()

        // Filter for enums that implement KeyedEnum
        val keyed = entries.filterIsInstance<KeyedEnum>()

        if (keyed.isNotEmpty()) {
            append("Please use one of the supported keys or names as an identifier.")
            append(" ")
            append("Supported keys: [${keyed.joinToString(ITEM_SEPARATOR) { it.key }}].")
        } else {
            append("Please use one of the supported names as an identifier.")
        }

        append(" ")
        append("Supported names: [${entries.joinToString(ITEM_SEPARATOR) { it.name }}].")
    }
)