package com.bigstackbully.rankmyhand.model.characteristic

import com.bigstackbully.rankmyhand.model.enums.PlayingCard

interface HasCards {
    val cards: List<PlayingCard>
}