package com.codelicia.advent2021

import Util.readInputAsList
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test as Theory

class Day12Test {

    @Theory
    fun part1() {
        assertEquals(10, Day12(SAMPLE_INPUT).part1())
        assertEquals(19, Day12(LARGER_SAMPLE_INPUT).part1())
        assertEquals(226, Day12(EVEN_LARGER_SAMPLE_INPUT).part1())
        assertEquals(5254, Day12(readInputAsList(12)).part1())
    }

    companion object {
        private val SAMPLE_INPUT = listOf(
            "start-A",
            "start-b",
            "A-c",
            "A-b",
            "b-d",
            "A-end",
            "b-end",
        )

        private val LARGER_SAMPLE_INPUT = listOf(
            "dc-end",
            "HN-start",
            "start-kj",
            "dc-start",
            "dc-HN",
            "LN-dc",
            "HN-end",
            "kj-sa",
            "kj-HN",
            "kj-dc",
        )

        private val EVEN_LARGER_SAMPLE_INPUT = listOf(
           "fs-end",
           "he-DX",
           "fs-he",
           "start-DX",
           "pj-DX",
           "end-zg",
           "zg-sl",
           "zg-pj",
           "pj-he",
           "RW-he",
           "fs-DX",
           "pj-RW",
           "zg-RW",
           "start-pj",
           "he-WI",
           "zg-he",
           "pj-fs",
           "start-RW",
        )
    }
}