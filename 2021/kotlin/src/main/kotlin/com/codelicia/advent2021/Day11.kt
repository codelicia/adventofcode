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
                    val next = (value + 1) % 10
                    grid[y][x] = next
                    if (next == 0) {
                        notifyNeighbors.add(y to x)
                        blocked.add(y to x)
                        totalFlashes++
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
                        totalFlashes++
                    }
                }
            }
        }

        return totalFlashes
    }

    fun part2(): Int {
        val grid = input.map { line ->
            line.split("").filterNot(String::isBlank).map(String::toInt).toMutableList()
        }.toMutableList()

        var loops = 0

        while (grid.flatten().filterNot { it == 0 }.isNotEmpty()) {
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
                    }
                }
            }
        }

        return loops
    }
}