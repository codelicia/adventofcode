package com.codelicia.advent2021

import java.util.Stack

class Day10(private val input: List<String>) {

    private val scoreTable: Map<String, Long> = mapOf(
        ")" to 3L,
        "]" to 57L,
        "}" to 1197L,
        ">" to 25137L,
    )

    private val scoreTableForIncomplete: Map<String, Long> = mapOf(
        "(" to 1L,
        "[" to 2L,
        "{" to 3L,
        "<" to 4L,
    )

    private val openChar = listOf("(", "[", "{", "<")
    private val closeChar = listOf(")", "]", "}", ">")

    fun part1(): Long {
        val queue = input.map { line -> ArrayDeque(line.split("")) }

        val order = ArrayDeque<String>()
        var points = 0L

        for (line in queue) {
            for (char in line.indices) {
                val lineChar = line[char]
                if (lineChar.isBlank()) continue

                if (openChar.contains(lineChar)) {
                    order.add(lineChar)
                } else {
                    val removed = order.removeLast()

                    if (openChar.zip(closeChar).first { it.first == removed }.second != lineChar) {
                        points += scoreTable[lineChar]!!
                    }
                }
            }
        }

        return points
    }

    fun part2(): Long {
        val queue = input.map { line -> ArrayDeque(line.split("")) }
        val closing = Stack<Long>()

        for (line in queue) {
            val order = ArrayDeque<String>()

            var error = false
            for (char in line.indices) {
                val lineChar = line[char]
                if (lineChar.isBlank() || error == true) continue

                if (openChar.contains(lineChar)) {
                    order.add(lineChar)
                } else {
                    val removed = order.removeLast()

                    if (openChar.zip(closeChar).first { it.first == removed }.second != lineChar) {
                        error = true
                        continue
                    }
                }
            }


            if (order.isNotEmpty() && error == false) {
                var total = 0L
                while (order.isNotEmpty()) {
                    val el = order.removeLast()
                    total *= 5
                    total += scoreTableForIncomplete[el]!!
                }
                closing.add(total)
            }
        }
        closing.sort()

        return closing[closing.size.floorDiv(2)]
    }
}