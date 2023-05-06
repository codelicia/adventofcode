package com.codelicia.advent2021

import Util.readInputAsList
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test as Theory

class Day11Test {

    @Theory
    fun part1() {
        assertEquals(1656, Day11(SAMPLE_INPUT).part1(steps = 100))
        assertEquals(1735, Day11(readInputAsList(11)).part1(steps = 100))
    }

    companion object {
        private val SAMPLE_INPUT = listOf(
           "5483143223",
           "2745854711",
           "5264556173",
           "6141336146",
           "6357385478",
           "4167524645",
           "2176841721",
           "6882881134",
           "4846848554",
           "5283751526",
        )
    }
}