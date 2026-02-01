package com.bigstackbully.rankmyhand.service.utils

fun String.hasEvenNumberOfCharacters(): Boolean = length % 2 == 0

fun String.wrapInApostrophes(): String = "'$this'"