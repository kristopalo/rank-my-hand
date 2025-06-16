package com.bigstackbully.rankmyhand.model.combination

import com.bigstackbully.rankmyhand.model.enums.HandRanking.STRAIGHT

val STRAIGHT_HANDS: HashMap<String, HandCombination> = hashMapOf(
        "AKQJT" to HandCombination("AKQJT", STRAIGHT, 1600, 0.785714, 1, 1.000000),
        "KQJT9" to HandCombination("KQJT9", STRAIGHT, 1601, 0.785580, 2, 0.900000),
        "QJT98" to HandCombination("QJT98", STRAIGHT, 1602, 0.785446, 3, 0.800000),
        "JT987" to HandCombination("JT987", STRAIGHT, 1603, 0.785312, 4, 0.700000),
        "T9876" to HandCombination("T9876", STRAIGHT, 1604, 0.785178, 5, 0.600000),
        "98765" to HandCombination("98765", STRAIGHT, 1605, 0.785044, 6, 0.500000),
        "87654" to HandCombination("87654", STRAIGHT, 1606, 0.784910, 7, 0.400000),
        "76543" to HandCombination("76543", STRAIGHT, 1607, 0.784776, 8, 0.300000),
        "65432" to HandCombination("65432", STRAIGHT, 1608, 0.784642, 9, 0.200000),
        "5432A" to HandCombination("5432A", STRAIGHT, 1609, 0.784508, 10, 0.100000)
)
