package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.enums.Suit
import org.springframework.stereotype.Service

@Service
class SuitService {
    fun getAllSuits(): List<Suit> = Suit.entries
}
