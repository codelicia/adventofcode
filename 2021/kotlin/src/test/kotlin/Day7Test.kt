package com.codelicia.advent2021

import Util.readInputAsIntList
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test as Theory

class Day7Test {

    @Theory
    fun part1() {
        assertEquals(37, Day7(SAMPLE_INPUT).part1())
        assertEquals(331067, Day7(readInputAsIntList(7)).part1())
    }

    companion object {
        private val SAMPLE_INPUT = listOf<Int>(
            16,
            1,
            2,
            0,
            4,
            2,
            7,
            1,
            2,
            14
        )
    }
}