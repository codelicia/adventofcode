package com.codelicia.advent2021

import java.util.SortedMap

class Day03(private val input: List<String>) {

    private val bitLength = input.first().length

    private fun List<Int>.toInt() =
        this.joinToString("").toInt(2)

    private fun List<Int>.gamma() = this.toInt()

    private fun List<Int>.epsilon() = this.map(::invertBit).toInt()

    private fun SortedMap<Int, Int>.bit(): Int =
        if (this.getValue(0) > this.getValue(1)) 0 else 1

    private fun SortedMap<Int, Int>.invBit(): Int =
        if (this.getValue(0) > this.getValue(1)) 1 else 0

    private fun Set<String>.countBits(n: Int): List<SortedMap<Int, Int>> =
        listOf(
            this.map { v -> v[n].digitToInt() }
                .groupingBy { it }
                .eachCount()
                .toSortedMap()
        )

    private val diagnosticReport = buildList {
        repeat(bitLength) { i ->
            val mostCommonBit = input
                .groupingBy { it[i].digitToInt() }
                .eachCount()
                .maxBy { it.value }
                .key

            add(mostCommonBit)
        }
    }

    private fun invertBit(n: Int) = if (n == 1) 0 else 1

    private fun calculateRating(
        remainingNumbers: Set<String>,
        predicate: (String, Int, List<SortedMap<Int, Int>>) -> Boolean,
        index: Int = 0,
    ): Int {
        if (remainingNumbers.count() == 1) return remainingNumbers.first().toInt(radix = 2)

        val sortedList = remainingNumbers.countBits(index)

        val filteredNumbers = remainingNumbers.filterNot { v -> predicate(v, index, sortedList) }.toSet()

        return calculateRating(filteredNumbers, predicate, index + 1)
    }

    fun part1(): Int = diagnosticReport.gamma() * diagnosticReport.epsilon()

    fun part2(): Int {

        val binaryNumbers = input.toSet()

        val co2ScrubberRating = calculateRating(
            binaryNumbers,
            { v, n, sl -> v[n].digitToInt() == sl[0].invBit() }
        )

        val oxygenGeneratorRating = calculateRating(
            binaryNumbers,
            { v, n, sl -> v[n].digitToInt() == sl[0].bit() }
        )

        return oxygenGeneratorRating * co2ScrubberRating
    }
}