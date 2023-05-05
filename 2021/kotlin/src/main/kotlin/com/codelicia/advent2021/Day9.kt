package com.codelicia.advent2021

import java.util.Stack

class Day9(private val input: List<String>) {

    private fun parseHeightmap(xs: List<String>): List<List<Int>> =
        xs.map { it.toCharArray().map(Char::digitToInt) }

    private fun List<List<Int>>.getLocation(p: Pair<Int, Int>): Int? =
        this.getOrNull(p.first)?.getOrNull(p.second)

    private fun Pair<Int, Int>.adjacentLocations() =
        listOf(
            this.first - 1 to this.second,
            this.first + 1 to this.second,
            this.first     to this.second + 1,
            this.first     to this.second - 1
        )

    private fun Stack<Int>.riskLevel(): Int =
        this.size + this.sum()

    fun part1(): Int {
        val heightmap = parseHeightmap(input)
        val lowPoints = Stack<Int>()

        heightmap.forEachIndexed { y, row ->
            row.forEachIndexed { x, cell ->
                var currentCellIsLower = true
                for (adjacent in (y to x).adjacentLocations()) {
                    val adjacentCell = heightmap.getLocation(adjacent)
                    if (adjacentCell != null && adjacentCell <= cell) currentCellIsLower = false
                }

                if (currentCellIsLower) lowPoints.add(cell)
            }
        }

        return lowPoints.riskLevel()
    }
}