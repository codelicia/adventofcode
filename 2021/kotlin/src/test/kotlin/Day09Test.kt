package com.codelicia.advent2021

import Util.readInputAsList
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test as Theory

class Day09Test {

    @Theory
    fun part1() {
        assertEquals(15, Day09(SAMPLE_INPUT).part1())
        assertEquals(580, Day09(readInputAsList(9)).part1())
    }

    @Theory
    fun part2() {
        assertEquals(1134, Day09(SAMPLE_INPUT).part2())
        assertEquals(856716, Day09(readInputAsList(9)).part2())
    }

    companion object {
        private val SAMPLE_INPUT = listOf(
            "2199943210",
            "3987894921",
            "9856789892",
            "8767896789",
            "9899965678"
        )
    }
}