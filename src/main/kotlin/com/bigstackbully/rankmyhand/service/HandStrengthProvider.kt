package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.EvaluationResult
import com.bigstackbully.rankmyhand.model.enums.HandRank
import com.bigstackbully.rankmyhand.model.handstrengths.HandStats
import org.springframework.stereotype.Service

@Service
class HandStrengthProvider {

    fun getHandStats(evaluationResult: EvaluationResult): HandStats {
        val shortNotation = evaluationResult.shortNotation

        val mapOfHandStats = when (evaluationResult.handRank) {
            HandRank.ROYAL_FLUSH -> mapOfAllRoyalFlushes()
            HandRank.STRAIGHT_FLUSH -> mapOfAllStraightFlushes()
            else -> error("Unknown hand rank")
        }

        val handStats = mapOfHandStats[shortNotation] ?: throw IllegalArgumentException("No such key found in the map of hands: $shortNotation")
        return handStats
    }

    fun mapOfAllFourOfAKinds(): HashMap<String, HandStats> = hashMapOf(
        // Ace
        "AAAAK" to HandStats(relativePosition = 1, absolutePosition = 11),
        "AAAAQ" to HandStats(relativePosition = 2, absolutePosition = 12),
        "AAAAJ" to HandStats(relativePosition = 3, absolutePosition = 13),
        "AAAAT" to HandStats(relativePosition = 4, absolutePosition = 14),
        "AAAA9" to HandStats(relativePosition = 5, absolutePosition = 15),
        "AAAA8" to HandStats(relativePosition = 6, absolutePosition = 16),
        "AAAA7" to HandStats(relativePosition = 7, absolutePosition = 17),
        "AAAA6" to HandStats(relativePosition = 8, absolutePosition = 18),
        "AAAA5" to HandStats(relativePosition = 9, absolutePosition = 19),
        "AAAA4" to HandStats(relativePosition = 10, absolutePosition = 20),
        "AAAA3" to HandStats(relativePosition = 11, absolutePosition = 21),
        "AAAA2" to HandStats(relativePosition = 12, absolutePosition = 22),

        // King
        "KKKKA" to HandStats(relativePosition = 13, absolutePosition = 23),
        "KKKKQ" to HandStats(relativePosition = 14, absolutePosition = 24),
        "KKKKJ" to HandStats(relativePosition = 15, absolutePosition = 25),
        "KKKKT" to HandStats(relativePosition = 16, absolutePosition = 26),
        "KKKK9" to HandStats(relativePosition = 17, absolutePosition = 27),
        "KKKK8" to HandStats(relativePosition = 18, absolutePosition = 28),
        "KKKK7" to HandStats(relativePosition = 19, absolutePosition = 29),
        "KKKK6" to HandStats(relativePosition = 20, absolutePosition = 30),
        "KKKK5" to HandStats(relativePosition = 21, absolutePosition = 31),
        "KKKK4" to HandStats(relativePosition = 22, absolutePosition = 32),
        "KKKK3" to HandStats(relativePosition = 23, absolutePosition = 33),
        "KKKK2" to HandStats(relativePosition = 24, absolutePosition = 34),
    )

    fun mapOfAllStraightFlushes(): HashMap<String, HandStats> = hashMapOf(
        "KQJT9" to HandStats(relativePosition = 1, absolutePosition = 2),
        "QJT98" to HandStats(relativePosition = 2, absolutePosition = 3),
        "JT987" to HandStats(relativePosition = 3, absolutePosition = 4),
        "T9876" to HandStats(relativePosition = 4, absolutePosition = 5),
        "98765" to HandStats(relativePosition = 5, absolutePosition = 6),
        "87654" to HandStats(relativePosition = 6, absolutePosition = 7),
        "76543" to HandStats(relativePosition = 7, absolutePosition = 8),
        "65432" to HandStats(relativePosition = 8, absolutePosition = 9),
        "5432A" to HandStats(relativePosition = 9, absolutePosition = 10)
    )

    fun mapOfAllRoyalFlushes(): HashMap<String, HandStats> = hashMapOf(
        "AKQJT" to HandStats(relativePosition = 1, absolutePosition = 1)
    )
}