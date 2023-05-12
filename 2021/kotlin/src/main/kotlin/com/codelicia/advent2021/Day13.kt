package com.codelicia.advent2021

import kotlin.math.max

class Day13(val input: String) {
    fun solve(folds: Int): Int {
        val (dots, fold) = input.split("\n\n")
        val xs = dots.split("\n").map { it.split(",").map { s -> s.toInt() } }

        val xMax = xs.maxOf { it[0] }
        val yMax = xs.maxOf { it[1] }

        var grid = Array(yMax + 1) { IntArray(xMax + 1) { 0 } }

        for ((x, y) in xs) {
            grid[y][x] = 1;
        }

        val foldInstructions = fold.split("\n").map { it.split(" ").last().split("=") }

        foldInstructions.forEachIndexed { loop, inst ->
            if (loop >= folds) return@forEachIndexed
            val (direction, value) = inst

            // Fold Y
            if (direction == "y") {
                val stay = grid.filterIndexed { k, _ -> k < value.toInt() }
                val fold = grid.filterIndexed { k, _ -> k > value.toInt() }.reversed()

                repeat(stay.size) { index ->
                    grid[index] = stay[index].mapIndexed { i, v -> max(fold[index][i], v) }.toIntArray()
                }
                grid = grid.copyOfRange(0, stay.lastIndex + 1)
            }

            // Fold X
            if (direction == "x") {
                val stay = grid.map { it.filterIndexed { k, _ -> k < value.toInt() } }
                val fold = grid.map { it.filterIndexed { k, _ -> k > value.toInt() }.reversed() }

                repeat(stay.size) { index ->
                    grid[index] = stay[index].mapIndexed { i, v -> max(fold[index][i], v) }.toIntArray()
                }
            }

        }

        return grid.sumOf { row -> row.sum() }
    }
}