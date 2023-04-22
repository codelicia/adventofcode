package com.codelicia.advent2021

import java.util.SortedMap

class Day3(private val input: List<String>) {

    private val forEachDigit = input.first().length

    private fun List<Int>.toInt() =
        this.joinToString("").toInt(2)

    private fun List<Int>.gamma() = this.toInt()

    private fun List<Int>.epsilon() = this.map(::invert).toInt()

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

    private fun reduce(
        s: MutableSet<String>,
        predicate: (String, Int, List<SortedMap<Int, Int>>) -> Boolean,
        n: Int = 0,
    ): Int {
        if (s.count() == 1) return s.first().toInt(radix = 2)

        val sl = s.rank(n)

        s.toList().forEach { v -> if (predicate(v, n, sl)) s.remove(v) }

        return reduce(s, predicate, n + 1)
    }

    fun part1(): Int = diagnosticReport.gamma() * diagnosticReport.epsilon()

    fun part2(): Int {

        val l = buildSet { input.forEach(::add) }

        val co3 = reduce(
            l.toMutableSet(),
            fun(v: String, n: Int, sl: List<SortedMap<Int, Int>>): Boolean =
                v.getOrElse(n) { '0' }.digitToInt() == sl.get(0).invBit()
        )

        val ox = reduce(
            l.toMutableSet(),
            fun(v: String, n: Int, sl: List<SortedMap<Int, Int>>): Boolean =
                v.getOrElse(n) { '0' }.digitToInt() == sl.get(0).bit()
        )

        return ox * co3
    }
}