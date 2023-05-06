package com.codelicia.advent2021

import org.junit.jupiter.api.Test as Theory
import kotlin.test.assertSame

class Day01Test {

    @Theory
    fun part1() = assertSame(7, Day01(SAMPLE_INPUT).part1())

    @Theory
    fun part2() = assertSame(5, Day01(SAMPLE_INPUT).part2())

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