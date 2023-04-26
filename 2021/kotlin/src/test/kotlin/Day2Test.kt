package com.codelicia.advent2021

import Util.readInputAsList
import org.junit.jupiter.api.Test as Theory
import kotlin.test.assertEquals

class Day2Test {

    @Theory
    fun part1() {
        assertEquals(150, Day2(SAMPLE_INPUT).part1())
        assertEquals(1989014, Day2(readInputAsList(2)).part1())
    }

    @Theory
    fun part2() {
        assertEquals(900, Day2(SAMPLE_INPUT).part2())
        assertEquals(2006917119, Day2(readInputAsList(2)).part2())
    }

    companion object {
        private val SAMPLE_INPUT = listOf(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2"
        )
    }
}