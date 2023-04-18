package com.codelicia.advent2021

import org.junit.jupiter.api.Test
import kotlin.test.assertSame

class Day1Test {

    @Test
    fun part1() = assertSame(7, Day1(SAMPLE_INPUT).part1())

    @Test
    fun part2() = assertSame(5, Day1(SAMPLE_INPUT).part2())

    companion object {
        private val SAMPLE_INPUT = listOf(
            199,
            200,
            208,
            210,
            200,
            207,
            240,
            269,
            260,
            263,
        )
    }
}