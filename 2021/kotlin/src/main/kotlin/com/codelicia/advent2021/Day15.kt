package com.codelicia.advent2021

import kotlin.math.min

class Day15(val input: String) {
    private val G = input.split("\n").map { it ->
        it.toCharArray().map { it.digitToInt() }
    }

    private val R = G.lastIndex
    private val C = G.last().lastIndex

    fun solve(): Int = minimumPath(0 to 0, mutableMapOf()) - G[0][0]

    private fun minimumPath(
        cur: Pair<Int, Int>,
        dp: MutableMap<Pair<Int, Int>, Int>,
    ): Int {
        val (r, c) = cur

        if (cur in dp) return dp[cur]!!

        if (r<0 || r>R || c<0 || c>C) return 1e9.toInt()
        if (r==R && c==C) return G[r][c]

        val ans = G[r][c] + min(minimumPath(r+1 to c, dp), minimumPath(r to c+1, dp))

        dp[r to c] = ans

        return ans
    }
}