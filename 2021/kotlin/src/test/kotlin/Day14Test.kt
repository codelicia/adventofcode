package com.codelicia.advent2021

import Util.readInput
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test as Theory

class Day14Test {

    @Theory
    fun part1() {
        assertEquals(1588, Day14(SAMPLE_INPUT).part1())
        assertEquals(4244, Day14(readInput(14)).part1())
    }

    companion object {
        private val SAMPLE_INPUT = """
        NNCB

        CH -> B
        HH -> N
        CB -> H
        NH -> C
        HB -> C
        HC -> B
        HN -> C
        NN -> C
        BH -> H
        NC -> B
        NB -> B
        BN -> B
        BB -> N
        BC -> B
        CC -> N
        CN -> C
       """.trimIndent()
    }
}