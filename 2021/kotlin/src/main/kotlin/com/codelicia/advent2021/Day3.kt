package com.codelicia.advent2021

import java.util.SortedMap
import kotlin.math.min

class Day3(private val input: List<String>) {

    private val forEachDigit = input.first().length

    private fun List<Int>.toInt() =
        this.joinToString("").toInt(2)

    private fun List<Int>.gamma() = this.toInt()

    private fun List<Int>.epsilon() = this.map(::invert).toInt()

    private fun SortedMap<Int, Int>.bit(): Int = if (this.getValue(0) > this.getValue(1)) 0 else 1
    private fun SortedMap<Int, Int>.invBit(): Int = if (this.getValue(0) > this.getValue(1)) 1 else 0

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

    private val grouped = buildList {
        repeat(forEachDigit) { i ->
            val ofBit = input
                .groupingBy { it[i].digitToInt() }
                .eachCount()
                .toSortedMap()

            add(ofBit)
        }
    }

    private fun invert(n: Int) = if (n == 1) 0 else 1

    fun part1(): Int = diagnosticReport.gamma() * diagnosticReport.epsilon()

    fun part2(): Int {

        fun reduce2(
            s: MutableSet<String>,
            n: Int,
            sl: List<SortedMap<Int, Int>>,
            predicate: (String, Int, List<SortedMap<Int, Int>>) -> Boolean
        ): Int {
            if (s.count() == 1) return s.first().toInt(radix = 2)

            s.toList().forEach { v ->
                if (predicate(v, n, sl)) s.remove(v)
            }

            return reduce2(s, n + 1, s.rank(min(s.first().length-1, n + 1)), predicate)
        }

        println("TOOOOO ${grouped}")
        val co3 = reduce2(
            buildSet(input.size) { input.forEach(::add) }.toMutableSet(),
            0,
            grouped,
            fun (v: String, n: Int, sl: List<SortedMap<Int, Int>>): Boolean {
                return v.getOrElse(n) { '0' }.digitToInt() == sl.get(0).invBit()
            }
        )
        val ox = reduce2(
            buildSet(input.size) { input.forEach(::add) }.toMutableSet(),
            0,
            grouped,
            fun (v: String, n: Int, sl: List<SortedMap<Int, Int>>): Boolean {
                return v.getOrElse(n) { '0' }.digitToInt() == sl.get(0).bit()
            }
        )
        println(ox)
        println(co3)

        return ox * co3
    }
}

private fun MutableSet<String>.rank(n: Int): List<SortedMap<Int, Int>> {
    println("initial list: ${this} on position ${n}")
    val x = listOf(
        this.map { v -> v[n].digitToInt() }
            .groupingBy { it }
            .eachCount()
            .toSortedMap()
    )

    println("shorted list: ${x}")
    return x
}
