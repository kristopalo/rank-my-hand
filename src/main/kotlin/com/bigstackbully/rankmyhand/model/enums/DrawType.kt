package com.bigstackbully.rankmyhand.model.enums

enum class DrawType {

    // --- Flush-related draws ---
    /** Four cards of the same suit (need one more for a flush). */
    FLUSH_DRAW,

    /** Three cards of the same suit on the flop (need runner-runner for a flush). */
    BACKDOOR_FLUSH,

    // --- Straight-related draws ---
    /** Open-ended straight draw: four consecutive ranks, needs one of two ends to complete (8 outs). */
    STRAIGHT_OPEN_ENDED,

    /** Gutshot (inside) straight draw: missing one specific rank in the middle (4 outs). */
    STRAIGHT_GUTSHOT,

    /** Double belly-buster (double gutshot) straight draw: two inside cards complete the straight (8 outs). */
    STRAIGHT_DOUBLE_GUTSHOT,

    /** Backdoor straight draw: need two perfect consecutive cards (runner-runner). */
    BACKDOOR_STRAIGHT,

    // --- Combination draws ---
    /** Both a straight draw and a flush draw at once (combo draw). */
    COMBO_STRAIGHT_FLUSH,

    /** Pair plus a flush draw. */
    PAIR_PLUS_FLUSH_DRAW,

    /** Pair plus an open-ended straight draw. */
    PAIR_PLUS_OESD,

    // --- Overcards / implied draws ---
    /** Two overcards to the board (6 outs to improve to top pair). */
    TWO_OVERCARDS,

    /** Overcards combined with a straight or flush draw (monster draw variant). */
    OVERCARDS_PLUS_DRAW,

    /** Combo draw that also includes overcards (e.g. A♠Q♠ on T♠J♠2♦). */
    MONSTER_DRAW
}