package com.codelicia.advent2021

class Day10(private val input: List<String>) {

    private val scoreTable: Map<String, Long> = mapOf(
        ")" to 3L,
        "]" to 57L,
        "}" to 1197L,
        ">" to 25137L,
    )

    fun part1(): Long {
        val queue = input.map { line -> ArrayDeque(line.split("")) }

        val order = ArrayDeque<String>()
        var points = 0L

        for (line in queue) {
            for (char in line.indices) {
                val lineChar = line[char]
                if (lineChar.isBlank()) continue
                if (lineChar == "(" || lineChar == "{" || lineChar == "<" || lineChar == "[") {
                    order.add(line[char])
                } else {
                    val removed = order.removeLast()
                    if (lineChar == ")" && removed != "("
                        || lineChar == "]" && removed != "["
                        || lineChar == ">" && removed != "<"
                        || lineChar == "}" && removed != "{"
                        ) {
                        points += scoreTable[lineChar]!!
                    }
                }
            }
        }

        return points
    }
}