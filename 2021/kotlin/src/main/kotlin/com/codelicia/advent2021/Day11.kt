package com.codelicia.advent2021

class Day11(private val input: List<String>) {

    fun part1(steps: Int): Int = solve(steps).first

    fun part2(): Int = solve(100).second

    private fun solve(steps: Int = 10): Pair<Int, Int> {
        val grid = input.map { line ->
                line.toCharArray().map(Char::digitToInt).toMutableList()
            }.toMutableList()

        var totalFlashes = 0
        var loops = 0
        while (grid.any { it.any { value -> value != 0 } }) {
            loops++

            val notifyNeighbors = ArrayDeque<Pair<Int, Int>>()
            val blocked = mutableSetOf<Pair<Int, Int>>()

            grid.forEachIndexed { y, line ->
                line.forEachIndexed { x, value ->
                    val next = (value + 1) % 10
                    grid[y][x] = next
                    if (next == 0) {
                        notifyNeighbors.add(y to x)
                        blocked.add(y to x)
                        if (loops <= steps) totalFlashes++
                    }
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
                    if (true == blocked.contains(neighbor)) continue
                    if (grid.getOrNull(neighbor.first)?.getOrNull(neighbor.second) == null) continue

                    val next = (grid[neighbor.first][neighbor.second] + 1) % 10
                    grid[neighbor.first][neighbor.second] = next

                    if (next == 0) {
                        notifyNeighbors.add(neighbor)
                        blocked.add(neighbor)
                        if (loops <= steps) totalFlashes++
                    }
                }
            }
        }

        return totalFlashes to loops
    }
}