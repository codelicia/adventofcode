package com.codelicia.advent2021

class Day02(plannedCourse: List<String>) {

    private val submarine =
        plannedCourse
            .map {
                val (orientation, to) = it.split(" ")
                orientation to to.toInt()
            }

    private fun List<Pair<String, Int>>.sumOfCoordinate(coordinate: String): Int =
        this.filter { it.first == coordinate }.sumOf { it.second }

    fun part1(): Int {

        val depth =
            submarine.sumOfCoordinate("down")
                .minus(submarine.sumOfCoordinate("up"))

        val horizontal = submarine.sumOfCoordinate("forward")

        return depth * horizontal
    }

    fun part2(): Int {

        var depth = 0
        var horizontal = 0
        var aim = 0

        submarine
            .forEach { x ->
                when (x.first) {
                    "down" -> aim += x.second
                    "up" -> aim -= x.second
                    "forward" -> {
                        depth += (aim * x.second)
                        horizontal += x.second
                    }
                }
            }

        return horizontal * depth
    }
}