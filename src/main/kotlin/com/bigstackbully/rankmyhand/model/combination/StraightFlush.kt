package com.bigstackbully.rankmyhand.model.combination

import com.bigstackbully.rankmyhand.model.enums.HandRanking.STRAIGHT_FLUSH

fun composeStraightFlushHands(): HashMap<String, HandCombination> = hashMapOf(
        "KQJT9" to HandCombination("KQJT9", STRAIGHT_FLUSH, 2, 0.999866, 1, 1.000000),
        "QJT98" to HandCombination("QJT98", STRAIGHT_FLUSH, 3, 0.999732, 2, 0.888889),
        "JT987" to HandCombination("JT987", STRAIGHT_FLUSH, 4, 0.999598, 3, 0.777778),
        "T9876" to HandCombination("T9876", STRAIGHT_FLUSH, 5, 0.999464, 4, 0.666667),
        "98765" to HandCombination("98765", STRAIGHT_FLUSH, 6, 0.999330, 5, 0.555556),
        "87654" to HandCombination("87654", STRAIGHT_FLUSH, 7, 0.999196, 6, 0.444444),
        "76543" to HandCombination("76543", STRAIGHT_FLUSH, 8, 0.999062, 7, 0.333333),
        "65432" to HandCombination("65432", STRAIGHT_FLUSH, 9, 0.998928, 8, 0.222222),
        "5432A" to HandCombination("5432A", STRAIGHT_FLUSH, 10, 0.998794, 9, 0.111111)
)
