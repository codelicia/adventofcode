package com.codelicia.advent2021

import java.util.*
import kotlin.math.min

class Day15(val input: String) {
    // Split the input string into a 2D grid of integers
    private val grid = input.split("\n").map { it ->
        it.toCharArray().map { it.digitToInt() }
    }

    private val maxRow = grid.lastIndex
    private val maxColumn = grid.last().lastIndex

    fun part1(): Int {
        val dp = Array(maxRow + 1) { IntArray(maxColumn + 1) { Int.MAX_VALUE } }
        dp[0][0] = 0

        for (i in 0..maxRow) {
            for (j in 0..maxColumn) {
                if (i > 0) dp[i][j] = min(dp[i][j], dp[i - 1][j] + grid[i][j])
                if (j > 0) dp[i][j] = min(dp[i][j], dp[i][j - 1] + grid[i][j])
            }
        }

        return dp[maxRow][maxColumn]
    }

    fun part2(): Int {
        val heap: PriorityQueue<Node> = PriorityQueue()
        heap.add(Node(0, 0, 0))

        val g = enlargeMap().split("\n").map { it ->
            it.toCharArray().map { it.digitToInt() }
        }

        val visited: MutableSet<Pair<Int, Int>> = mutableSetOf()

        while (heap.isNotEmpty()) {
            val node = heap.poll()
            val cost = node.cost
            val x = node.x
            val y = node.y
            if (x == g.lastIndex && y == g.last().lastIndex) {
                return cost
            }
            if (x to y in visited) continue

            visited.add(x to y)

            for ((nx, ny) in listOf(x to y-1, x to y+1, x-1 to y, x+1 to y)) {
                if (nx < 0 || ny < 0 || nx > g.lastIndex || ny > g.last().lastIndex) continue
                if (nx to ny in visited) continue
                heap.add(Node(cost + g[nx][ny], nx, ny))
            }
        }

        error("Could not find the shortest path")
    }

    fun Int.inc(i: Int): Int = if (this + i > 9) (this + i) % 9 else this+i

    fun enlargeMap(): String {
        // pad right
        var s : String = ""
        for (i in 0..maxRow) {
            repeat(5) {
                repeatIdx -> s += grid[i].map { it.inc(repeatIdx) }.joinToString("")
            }
            s += "\n"
        }

        // pad bottom
        repeat(4) { rp ->
            var paddedGrid = s.split("\n").map { it -> it.toCharArray().map { it.digitToInt() } }

            for (i in 0..maxRow) {
                s += paddedGrid[i].map { it.inc(rp+1) }.joinToString("") + "\n"
            }
        }

        return s.trim()
    }

    private class Node(val cost: Int, val x: Int, val y: Int) : Comparable<Node> {
        override fun compareTo(other: Node): Int {
            if (other.cost > cost) return -1
            if (other.cost < cost) return 1
            return 0
        }

        override fun toString(): String = "Node(cost=$cost, x=$x, y=$y)"
    }
}