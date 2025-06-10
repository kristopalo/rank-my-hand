package com.bigstackbully.rankmyhand.service

import com.bigstackbully.rankmyhand.model.EvaluationResult
import com.bigstackbully.rankmyhand.model.enums.HandRank
import com.bigstackbully.rankmyhand.model.handstrengths.HandStrength
import com.bigstackbully.rankmyhand.model.handstrengths.RoyalFlushHandStrengths
import org.springframework.stereotype.Service

@Service
class HandStrengthCalculatorService {

//    fun getHandStrength(evaluationResult: EvaluationResult): HandStrength {
//        val rankAbbreviations = evaluationResult.rankUnitsStdNtn
//        val handStrength = when (evaluationResult.rank) {
//            HandRank.ROYAL_FLUSH -> getHandStrengthOfRoyalFlush()
//            else -> error("Unknown hand rank")
//        }
//    }
//
//    private fun getHandStrengthOfRoyalFlush(): HandStrength {
//        RoyalFlushHandStrengths.values.get()
//    }
}