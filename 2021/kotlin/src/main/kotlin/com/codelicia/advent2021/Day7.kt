package com.codelicia.advent2021

import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min

class Day7(private val crabs: List<Int>) {

    fun part1(): Int {
        var fuelConsumption = Int.MAX_VALUE

        for (horizontalPosition in crabs.min()..crabs.max()) {
            val fuelConsumptionCalculation = crabs.sumOf { x -> (horizontalPosition - x).absoluteValue }

            if (fuelConsumption > fuelConsumptionCalculation) {
                fuelConsumption = fuelConsumptionCalculation
            }
        }

        return fuelConsumption
    }

    fun part2(): Int {
        var fuelConsumption = Int.MAX_VALUE

        for (horizontalPosition in 1..crabs.max()) {
            val fuelConsumptionCalculation = crabs.map { x ->
//                print("x: $x, horizontalPosition: $horizontalPosition")
                var steps = 1..(x - horizontalPosition).absoluteValue
                val m = steps.toMutableList()
//                print(", steps: $steps")
                val su = m.mapIndexed { index, i ->
                    val previous = m.getOrElse(index - 1) { 0 }
                    previous + 1
//                    println(" - previous: $previous, i: $i")
//                    previous + 1
                }.sum()
//                println(" - SUM: $su\n")
                su
            }.sum()

            if (fuelConsumption > fuelConsumptionCalculation) {
                fuelConsumption = fuelConsumptionCalculation
            }
        }

        return fuelConsumption
    }
}