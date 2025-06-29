package com.bigstackbully.rankmyhand.model.exception

import com.bigstackbully.rankmyhand.model.enums.interfaces.KeyedEnum
import kotlin.reflect.KClass

const val ITEM_SEPARATOR = ", "

class EnumNotFoundException(
    identifier: String,
    enumClass: KClass<out Enum<*>>
) : RuntimeException(
    buildString {
        append("Enum value not found with the following identifier: '$identifier'.")
        append(" ")
        append("Please choose the identifier's value from the list of supported keys or names in ${enumClass.simpleName}.")
        append(" ")

        // Get enum entries using reflection
        val entries = enumClass.java.enumConstants?.toList().orEmpty()

        // Filter for enums that implement KeyedEnum
        val keyed = entries.filterIsInstance<KeyedEnum>()

        if (keyed.isNotEmpty()) {
            append("Supported keys: [${keyed.joinToString(ITEM_SEPARATOR) { it.key }}].")
        }
        append(" ")
        append("Supported names: [${entries.joinToString(ITEM_SEPARATOR) { it.name }}].")
    }
)