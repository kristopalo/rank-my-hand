package com.bigstackbully.rankmyhand.model.characteristic

import com.bigstackbully.rankmyhand.model.enums.Card

interface HasCards {
    val cards: List<Card>
}