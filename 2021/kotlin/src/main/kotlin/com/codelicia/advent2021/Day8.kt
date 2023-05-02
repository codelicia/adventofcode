package com.codelicia.advent2021

class Day8(private val signals: List<String>) {

    fun part1(): Int {
        return signals.map { it.split(" | ")[1].split(" ") }
            .map{ sig -> sig.map {
                when (it.length) {
                    2, 3, 4, 7 -> 1
                    else -> 0
                }
            }
        }.sumOf { it.sum() }
    }
}