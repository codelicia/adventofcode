package com.codelicia.advent2021

class Day8(private val signals: List<String>) {

    data class SegmentMap(
        val map: Map<String, Int>,
        val decodedNumber: Int
    ) {
        companion object {
            private fun String.order() =
                this.split("").sortedDescending().joinToString(separator = "")

            fun of(input: String): SegmentMap {
                val (segment, numbers) = input.split(" | ")

                val segmentSplit = segment.split(" ")

                // Hacky ones
                val sixDigits = segmentSplit.filter { it.length == 6 }
                val fiveDigits = segmentSplit.filter { it.length == 5 }

                // Easy discoverable
                val one = segmentSplit.filter { it.length == 2 }[0]
                val four = segmentSplit.filter { it.length == 4 }[0]
                val seven = segmentSplit.filter { it.length == 3 }[0]
                val eight = segmentSplit.filter { it.length == 7 }[0]

                // Tricky
                val nine = sixDigits.filter { it.split("").containsAll(four.split("")) }[0]
                val zero = sixDigits.filter { !it.split("").containsAll(nine.split("")) }
                    .filter { it.split("").containsAll(one.split("")) }[0]
                val six = sixDigits.filter { !it.split("").containsAll(nine.split("")) }
                    .filter { !it.split("").containsAll(one.split("")) }[0]

                val three = fiveDigits.filter { it.split("").containsAll(one.split("")) }[0]
                val five = fiveDigits.filter { six.split("").containsAll(it.split("")) }[0]
                val two = fiveDigits.filter { !it.split("").containsAll(three.split("")) }
                    .filter { !it.split("").containsAll(five.split("")) }[0]

                val map = mutableMapOf<String, Int>()
                map[zero.order()] = 0
                map[one.order()] = 1
                map[two.order()] = 2
                map[three.order()] = 3
                map[four.order()] = 4
                map[five.order()] = 5
                map[six.order()] = 6
                map[seven.order()] = 7
                map[eight.order()] = 8
                map[nine.order()] = 9

                val number = numbers.split(" ").map {
                    map[it.order()]!!
                }

                return SegmentMap(map, number.joinToString(separator = "").toInt())
            }
        }
    }

    private val easyDigits = listOf(2, 3, 4, 7)

    fun part1(): Int =
        signals.map { it.split(" | ")[1].split(" ") }
            .map { segments ->
                segments.map {
                    when {
                        easyDigits.contains(it.length) -> 1
                        else -> 0
                    }
                }
            }.sumOf { it.sum() }

    fun part2(): Int =
        signals.map { SegmentMap.of(input = it) }
        .sumOf { it.decodedNumber }
}