package com.codelicia.advent2021

import Util.readInput
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test as Theory

class Day14Test {

    @Theory
    fun part1() {
        assertEquals(1588, Day14(SAMPLE_INPUT).solve(10))
        assertEquals(4244, Day14(readInput(14)).solve(10))
    }

    @Theory
    fun part2() {
        assertEquals(2188189693529, Day14(SAMPLE_INPUT).solve(40))
        assertEquals(4807056953866, Day14(readInput(14)).solve(40))
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