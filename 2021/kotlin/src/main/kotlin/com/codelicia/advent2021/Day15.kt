package com.codelicia.advent2021

import java.util.*
import kotlin.math.min

class Node(val cost: Int, val x: Int, val y: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        if (other.cost > cost) return -1
        if (other.cost < cost) return 1
        return 0
    }

    override fun toString(): String {
        return "Node(cost=$cost, x=$x, y=$y)"
    }
}

class Day15(val input: String) {
    // Split the input string into a 2D grid of integers
    private val grid = input.split("\n").map { it ->
        it.toCharArray().map { it.digitToInt() }
    }

    private val enlargedGrid = enlargeMap().split("\n").map { it ->
        it.toCharArray().map { it.digitToInt() }
    }

    // Get the maximum row and column indices of the grid
    private val enlargedMaxRow = enlargedGrid.lastIndex
    private val enlargedMaxColumn = enlargedGrid.last().lastIndex

    // Get the maximum row and column indices of the grid
    private val maxRow = grid.lastIndex
    private val maxColumn = grid.last().lastIndex

    // Find the minimum path sum from the top-left corner to the bottom-right corner of the grid
    fun solve(): Int = minimumPath(0 to 0, mutableMapOf()) - enlargedGrid[0][0]

    // Solving it the recursive way
    // Recursive function to find the minimum path sum from a given cell to the bottom-right corner of the grid
    private fun minimumPath(
        cur: Pair<Int, Int>, // Current cell coordinates
        dp: MutableMap<Pair<Int, Int>, Int>, // Memoization map to store previously computed results
    ): Int {
        val (r, c) = cur

        // If the result for the current cell has already been computed, return it
        if (cur in dp) return dp[cur]!!

        // If the current cell is out of bounds, return a large value to indicate that it is not a valid path
        if (r<0 || r>enlargedMaxRow || c<0 || c>enlargedMaxColumn) return 1e9.toInt()

        // If the current cell is the bottom-right corner, return its value
        if (r==enlargedMaxRow && c==enlargedMaxColumn) return enlargedGrid[r][c]

        // Compute the minimum path sum from the current cell to the bottom-right corner by recursively computing
        // the minimum path sum from the cell below and the cell to the right, and adding the current cell's value
        val ans = enlargedGrid[r][c] + min(minimumPath(r+1 to c, dp), minimumPath(r to c+1, dp))

        // Store the result in the memoization map and return it
        dp[cur] = ans
        return ans
    }

    // Solving it the non-recursive way
    fun part1(): Int {
        // I still don't know for sure what Dynamic Programming is about dough...
        val dp = Array(maxRow + 1) { IntArray(maxColumn + 1) { Int.MAX_VALUE } }
        dp[0][0] = 0

        // The time complexity of this code is O(n^2), where n is the size of the grid.
        // The space complexity is also O(n^2), since we are using a 2D array to store
        // the minimum path sum for each cell.
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

    private fun Int.incDiv(): Int = this.inc(1)

    fun enlargeMap(): String {
        // pad right
        var s : String = ""
        for (i in 0..maxRow) {
            repeat(5) { repeatIdx ->
                s += grid[i].map { it.inc(repeatIdx) }.joinToString("")
            }
            s += "\n"
        }
        var t = ""

        var temp = ""

        // pad bottom
        repeat(4) { rp ->
            var paddedGrid = s.split("\n").map { it ->
                it.toCharArray().map { it.digitToInt() }
            }

            t = ""
            for (i in 0..maxRow) {
                t += paddedGrid[i].map { it.inc(rp+1) }.joinToString("")
                t += "\n"
            }

            temp += t
        }

        s += temp

        return s.trim()
    }
}