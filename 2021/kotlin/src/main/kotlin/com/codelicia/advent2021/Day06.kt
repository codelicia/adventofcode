package com.codelicia.advent2021

class Day06(input: String) {

    private var map: MutableList<Long> = mutableListOf<Long>().apply {
        (0..8).forEach { _ -> this.add(0L) }
    }

    init {
        input.split(",").map { map.set(it.toInt(), map[it.toInt()] + 1L) }
    }

    fun part1(days: Int = 80): Long {
        // Rotate map and add quantity on 0 to 6
        repeat(days) {
            val first = map.removeFirst()
            map.add(first)
            map[6] += first
        }

        return map.sum()
    }

    fun part2(days: Int = 256) = part1(days)
}