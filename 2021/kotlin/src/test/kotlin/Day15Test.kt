package com.codelicia.advent2021

import Util.readInput
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test as Theory

class Day15Test {

    @Theory
    fun part1() {
        assertEquals(40, Day15(SAMPLE_INPUT).solve())
        assertEquals(602, Day15(readInput(15)).solve())
    }

    companion object {
        private val SAMPLE_INPUT = """
            1163751742
            1381373672
            2136511328
            3694931569
            7463417111
            1319128137
            1359912421
            3125421639
            1293138521
            2311944581
       """.trimIndent()
    }
}