package com.codelicia.advent2021

import Util.readInput
import org.junit.jupiter.api.Test as Theory
import kotlin.test.assertEquals

class Day05Test {

    @Theory
    fun part1() {
        assertEquals(5, Day05(SAMPLE_INPUT).part1())
        assertEquals(5608, Day05(readInput(5)).part1())
    }

    @Theory
    fun part2() {
        assertEquals(0, Day05("9,7 -> 7,9").part2())
        assertEquals(0, Day05("1,1 -> 3,3").part2())
        assertEquals(12, Day05(SAMPLE_INPUT).part2())
        assertEquals(20299, Day05(readInput(5)).part2())
    }

    companion object {
        private const val SAMPLE_INPUT = """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
        """
    }
}