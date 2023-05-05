package com.codelicia.advent2021

import Util.readInputAsList
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test as Theory

class Day10Test {

    @Theory
    fun part1() {
        assertEquals(26397, Day10(SAMPLE_INPUT).part1())
        assertEquals(26397, Day10(readInputAsList(10)).part1())
    }

    companion object {
        private val SAMPLE_INPUT = listOf(
            "[({(<(())[]>[[{[]{<()<>>",
            "[(()[<>])]({[<{<<[]>>(",
            "{([(<{}[<>[]}>{[]{[(<()>",
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",
            "[{[{({}]{}}([{[{{{}}([]",
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",
            "<{([([[(<>()){}]>(<<{{",
            "<{([{{}}[<[[[<>{}]]]>[]]",
        )
    }
}