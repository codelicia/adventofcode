package com.codelicia.advent2021

import java.util.Stack

class Day09(private val input: List<String>) {

    private fun parseHeightmap(xs: List<String>): List<List<Int>> =
        xs.map { it.toCharArray().map(Char::digitToInt) }

    private fun <T> List<List<T>>.getLocation(p: Pair<Int, Int>): T? =
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

    fun part2(): Int {
        val heightmap = parseHeightmap(input)
        val basins = mutableListOf<MutableList<Int>>()

        heightmap.forEachIndexed { y, row ->
            row.forEachIndexed { x, cell ->
                var currentCellIsLower = true
                for (adjacent in (y to x).adjacentLocations()) {
                    val adjacentCell = heightmap.getLocation(adjacent)
                    if (adjacentCell != null && adjacentCell < cell) currentCellIsLower = false
                }

                if (currentCellIsLower) {
                    basins.add(findSmallerFrom(heightmap, y to x, mutableSetOf())!!)
                }
            }
        }

        return basins.map { it.size }.sortedDescending().take(3).reduce {
            x, y -> x * y
        }
    }

    private fun findSmallerFrom(heightmap: List<List<Int>>, p: Pair<Int, Int>, visited: MutableSet<Pair<Int, Int>>): MutableList<Int>? {

        val cell = heightmap.getLocation(p) ?: return null

        val lowPoints = mutableListOf<Int>()

        lowPoints.add(cell)
        visited.add(p)
        for (adjacent in p.adjacentLocations()) {
            val adjacentCell = heightmap.getLocation(adjacent)
            if (adjacentCell != null && adjacentCell > cell && adjacentCell != 9 && false == visited.contains(adjacent)) {
                val x = findSmallerFrom(heightmap, adjacent, visited);
                if (x != null) {
                    lowPoints.addAll(x)
                }
            }
        }

        return lowPoints
    }
}