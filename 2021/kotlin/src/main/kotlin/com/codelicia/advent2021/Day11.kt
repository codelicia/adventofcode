package com.codelicia.advent2021

class Day11(private val input: List<String>) {

    fun part1(steps: Int): Int {
        val grid = input.map { line ->
            line.split("").filterNot(String::isBlank).map(String::toInt).toMutableList()
        }.toMutableList()

        var totalFlashes = 0

        repeat(steps) {
            val notifyNeighbors = ArrayDeque<Pair<Int, Int>>()
            val blocked = mutableSetOf<Pair<Int, Int>>()

            grid.forEachIndexed { y, line ->
                line.forEachIndexed { x, value ->
                    val next = value + 1
                    grid[y][x] = if (next > 9) {
                        notifyNeighbors.add(y to x)
                        blocked.add(y to x)
                        totalFlashes++
                        0
                    } else next
                }
            }

            while (notifyNeighbors.isNotEmpty()) {
                val (y, x) = notifyNeighbors.removeFirst()

                for (neighbor in listOf(
                    y to x + 1,
                    y to x - 1,
                    y - 1 to x,
                    y + 1 to x,
                    y + 1 to x + 1,
                    y - 1 to x - 1,
                    y + 1 to x - 1,
                    y - 1 to x + 1,
                )) {
                    if (grid.getOrNull(neighbor.first)?.getOrNull(neighbor.second) == null || true == blocked.contains(neighbor)) {
                        continue
                    }

                    val value = grid[neighbor.first][neighbor.second] + 1
                    grid[neighbor.first][neighbor.second] = if (value > 9) {
                        notifyNeighbors.add(neighbor)
                        blocked.add(neighbor)
                        totalFlashes++
                        0
                    } else value
                }
            }
        }

        return totalFlashes
    }
}