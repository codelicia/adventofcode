package com.codelicia.advent2021

import kotlin.math.absoluteValue
import kotlin.math.min

class Day07(private val crabs: List<Int>) {

    private fun List<Int>.range() = this.min()..this.max()

    fun part1(): Int = crabs.range()
        .minOfOrNull { position -> crabs.sumOf { crab -> (position - crab).absoluteValue } }!!

    fun part2(): Int {
        var fuelConsumption = Int.MAX_VALUE

        val memoization = HashMap<Int, Int>()
        for (horizontalPosition in 1..crabs.max()) {
            val previous = memoization.getOrElse(horizontalPosition - 1) { 0 }
            memoization[horizontalPosition] = previous + 1
        }

        for (horizontalPosition in 1..crabs.max()) {
            fuelConsumption = min(fuelConsumption, crabs.map { x ->
                memoization.values.take((x - horizontalPosition).absoluteValue).sum()
            }.sum())
        }

        return fuelConsumption
    }
}