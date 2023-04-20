package com.codelicia.advent2021

class Day3(private val input: List<String>) {

    private val forEachDigit = input.first().length

    private fun List<Int>.toInt() =
        this.joinToString("").toInt(2)

    private val diagnosticReport = buildList {
        repeat(forEachDigit) { i ->
            val ofBit = input
                .groupingBy { it[i].digitToInt() }
                .eachCount()
                .maxBy { s -> s.value }
                .key

            add(ofBit)
        }
    }

    private fun invert(n: Int) = if (n == 1) 0 else 1

    fun part1(): Int = diagnosticReport.toInt() * diagnosticReport.map(::invert).toInt()

    fun part2(): Int {
        val gamma = diagnosticReport.toInt()
        val epsilon = diagnosticReport.map(::invert).toInt()

        return gamma * epsilon
    }
}