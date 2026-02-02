package com.bigstackbully.rankmyhand.model.exception

import com.bigstackbully.rankmyhand.model.enums.interfaces.KeyedEnum
import com.bigstackbully.rankmyhand.utils.ITEM_SEPARATOR
import com.bigstackbully.rankmyhand.utils.SINGLE_SPACE
import kotlin.reflect.KClass

class EnumNotFoundException(
    identifier: String,
    enumClass: KClass<out Enum<*>>
) : IllegalArgumentException(
    buildList {
        add("Invalid identifier '$identifier'.")
        add("No matching value found in enum class '${enumClass.simpleName}'.")

        // Get enum entries using reflection
        val entries = enumClass.java.enumConstants?.toList().orEmpty()

        // Filter for enums that implement KeyedEnum
        val keyed = entries.filterIsInstance<KeyedEnum>()

        if (keyed.isNotEmpty()) {
            add("Please use one of the supported keys or names as an identifier.")
            add("Supported keys: [${keyed.joinToString(ITEM_SEPARATOR) { it.key }}].")
        } else {
            add("Please use one of the supported names as an identifier.")
        }

        add("Supported names: [${entries.joinToString(ITEM_SEPARATOR) { it.name }}].")
    }.joinToString(SINGLE_SPACE)
)