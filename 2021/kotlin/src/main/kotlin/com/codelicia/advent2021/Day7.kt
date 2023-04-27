package com.codelicia.advent2021

class Day7(private val input: List<Int>) {

    fun part1(): Int {
        var minimum = 0 to Int.MAX_VALUE
        for (i in input.min()..input.max()) {
            val count = input.map { x ->
                if (x > i) {
                    (i until x).count()
                } else if (x < i) {
                    (x until i).count()
                } else {
                    0
                }
            }.sum()

            if (minimum.second > count) {
                minimum = i to count
            }
        }

        return minimum.second
    }
}