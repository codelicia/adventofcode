package com.codelicia.advent2021

class Day1(private val sonarReport: List<Int>) {

    fun part1(): Int =
        sonarReport
            .zipWithNext()
            .count { it.second > it.first }

    fun part2(): Int =
        sonarReport
            .windowed(size = 3, step = 1) { it.sum() }
            .zipWithNext()
            .count { it.second > it.first }
}