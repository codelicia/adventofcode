package com.codelicia.advent2021

import Util.readInput
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test as Theory

class Day13Test {

    @Theory
    fun part1() {
        assertEquals(17, Day13(SAMPLE_INPUT).solve(folds = 1))
        assertEquals(765, Day13(readInput(13)).solve(folds = 1))
    }

    @Theory
    fun part2() {
        assertEquals(16, Day13(SAMPLE_INPUT).solve(folds = 1e9.toInt() + 5))
        assertEquals(98, Day13(readInput(13)).solve(folds = 1e9.toInt() + 5))
    }

    companion object {
        private val SAMPLE_INPUT = """
           6,10
           0,14
           9,10
           0,3
           10,4
           4,11
           6,0
           6,12
           4,1
           0,13
           10,12
           3,4
           3,0
           8,4
           1,10
           2,14
           8,10
           9,0
           
           fold along y=7
           fold along x=5
       """.trimIndent()
    }
}