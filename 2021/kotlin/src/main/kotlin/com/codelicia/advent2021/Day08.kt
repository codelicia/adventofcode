package com.codelicia.advent2021

class Day08(private val signals: List<String>) {

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
                val one = segmentSplit.first { it.length == 2 }
                val four = segmentSplit.first { it.length == 4 }
                val seven = segmentSplit.first { it.length == 3 }
                val eight = segmentSplit.first { it.length == 7 }

                // Tricky
                val nine = sixDigits.first { it.split("").containsAll(four.split("")) }
                val zero = sixDigits.filter { !it.split("").containsAll(nine.split("")) }
                    .first { it.split("").containsAll(one.split("")) }
                val six = sixDigits.filter { !it.split("").containsAll(nine.split("")) }
                    .first { !it.split("").containsAll(one.split("")) }

                val three = fiveDigits.first { it.split("").containsAll(one.split("")) }
                val five = fiveDigits.first { six.split("").containsAll(it.split("")) }
                val two = fiveDigits.filter { !it.split("").containsAll(three.split("")) }
                    .first { !it.split("").containsAll(five.split("")) }

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