package com.codelicia.advent2021

import Util.readInput
import org.junit.jupiter.api.Test as Theory
import kotlin.test.assertEquals

class Day06Test {

    @Theory
    fun part1() {
        assertEquals(5934, Day06(SAMPLE_INPUT).part1())
        assertEquals(350149, Day06(readInput(6)).part1())
    }

    @Theory
    fun part2() {
        assertEquals(26984457539, Day06(SAMPLE_INPUT).part2())
        assertEquals(1590327954513, Day06(readInput(6)).part2())
    }

    companion object {
        private const val SAMPLE_INPUT = "3,4,3,1,2"
    }
}