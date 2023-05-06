package com.codelicia.advent2021

import kotlin.math.max
import kotlin.math.min

typealias Vent = Pair<Int, Int>
typealias VentLine = Pair<Vent, Vent>

class Day05(input: String) {

    private val vents: List<VentLine> = input.trimIndent()
        .lines()
        .map { x ->
            val xs = x.split(" -> ")

            Vent(xs[0]) to Vent(xs[1])
        }

    fun part1(): Int = overlaps { vent -> vent.isDiagonal() }

    fun part2(): Int = overlaps { false }

    private fun VentLine.diagonalRange(): List<Vent> =
        (if (first.second < second.second) horizontalRange().reversed() else horizontalRange())
            .zip(if (first.first < second.first) verticalRange().reversed() else verticalRange())

    private fun VentLine.horizontalRange(): IntRange =
        min(first.first, second.first)..max(first.first, second.first)

    private fun VentLine.verticalRange(): IntRange =
        min(first.second, second.second)..max(first.second, second.second)

    private fun VentLine.isDiagonal() =
        first.first != second.first && first.second != second.second

    private fun VentLine.isHorizontal() =
        first.first != second.first

    private fun VentLine.isVertical() =
        first.second != second.second

    private fun overlaps(predicate: (VentLine) -> Boolean): Int =
        vents.map { line ->
            return@map when {
                predicate(line) -> emptyList<String>()
                line.isDiagonal() -> line.diagonalRange().map { "${it.first},${it.second}" }
                line.isHorizontal() -> line.horizontalRange().map { "$it,${line.first.second}" }
                line.isVertical() -> line.verticalRange().map { "${line.first.first},$it" }
                else -> emptyList<String>()
            }
        }
            .flatten()
            .groupingBy { it }
            .eachCount()
            .count { it.value > 1 }

    companion object {
        private fun Vent(s: String): Vent =
            s.split(",").first().toInt() to s.split(",").last().toInt()
    }
}