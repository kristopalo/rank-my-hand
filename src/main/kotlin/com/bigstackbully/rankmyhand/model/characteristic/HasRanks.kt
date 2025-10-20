package com.bigstackbully.rankmyhand.model.characteristic

import com.bigstackbully.rankmyhand.model.enums.Rank

interface HasRanks {
    val ranks: List<Rank>
}