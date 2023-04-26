package com.codelicia.advent2021

import Util.readInputAsList
import org.junit.jupiter.api.Test as Theory
import kotlin.test.assertEquals

class Day3Test {

    @Theory
    fun part1() {
        assertEquals(198, Day3(SAMPLE_INPUT).part1())
        assertEquals(4118544, Day3(readInputAsList(3)).part1())
    }

    @Theory
    fun part2() {
        assertEquals(230, Day3(SAMPLE_INPUT).part2())
        assertEquals(3832770, Day3(readInputAsList(3)).part2())
    }

    companion object {
        private val SAMPLE_INPUT = listOf(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010",
        )
    }
}