package com.codelicia.advent2021

class Day7(private val input: List<Int>) {

    fun part1(): Int {
        var fuelConsumption = Int.MAX_VALUE

        for (horizontalPosition in input.min()..input.max()) {
            val fuelConsumptionCalculation = input.sumOf { x ->
                when {
                    x > horizontalPosition -> (horizontalPosition until x).count()
                    x < horizontalPosition -> (x until horizontalPosition).count()
                    else -> 0
                }
            }

            if (fuelConsumption > fuelConsumptionCalculation) {
                fuelConsumption = fuelConsumptionCalculation
            }
        }

        return fuelConsumption
    }
}