package com.codelicia.advent2021

import java.util.SortedMap
import kotlin.math.absoluteValue

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

    private fun MutableSet<String>.rank(n: Int): List<SortedMap<Int, Int>> =
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
        s: MutableSet<String>,
        predicate: (String, Int, List<SortedMap<Int, Int>>) -> Boolean,
        n: Int = 0,
    ): Int {
        if (s.count() == 1) return s.first().toInt(radix = 2)

        val sl = s.rank(n)

        s.toList().forEach { v -> if (predicate(v, n, sl)) s.remove(v) }

        return calculateRating(s, predicate, n + 1)
    }

    fun part1(): Int = diagnosticReport.gamma() * diagnosticReport.epsilon()

    fun part2(): Int {

        val binaryNumbers = buildSet { input.forEach(::add) }

        val co2ScrubberRating = calculateRating(
            binaryNumbers.toMutableSet(),
            { v, n, sl -> v[n].digitToInt() == sl[0].invBit() }
        )

        val oxygenGeneratorRating = calculateRating(
            binaryNumbers.toMutableSet(),
            { v, n, sl -> v[n].digitToInt() == sl[0].bit() }
        )

        return oxygenGeneratorRating * co2ScrubberRating
    }
}