package com.codelicia.advent2021

class Day14(val input: String) {
    fun solve(times: Int): Long {
        val polymerTemplate = input.split("\n\n").take(1).first().trim().split("").filterNot { it.isBlank() }

        // Mount hashMap with values
        val hashMap = HashMap<String, String>()
            .apply {
                input.split("\n\n")
                    .drop(1)
                    .first()
                    .split("\n")
                    .map { it.split(" -> ") }
                    .forEach { set(it.first(), it.last()) }
            }

        var count = mutableMapOf<String, Long>()

        for (index in 0 until polymerTemplate.indices.last) {
            val x = polymerTemplate[index] + polymerTemplate[index + 1]
            count.putIfAbsent(x, 0)
            count[x] = count[x]!! + 1
        }

        repeat(times) {
            val c2 = HashMap<String, Long>()
            count.forEach { index ->
                val v = hashMap[index.key]!!
                val x = index.key[0] + v
                val y = v + index.key[1]
                c2.putIfAbsent(x, 0L)
                c2.putIfAbsent(y, 0L)
                c2[x] = c2[x]!! + index.value
                c2[y] = c2[y]!! + index.value
            }
            count = c2
        }

        val c2 = HashMap<String, Long>()
        count.forEach { index ->
            val x = index.key[0].toString()
            c2.putIfAbsent(x, 0L)
            c2[x] = c2[x]!! + index.value
        }

        // Bene nota: the last char never changes
        c2[polymerTemplate.last()] = c2[polymerTemplate.last()]!! + 1

        return c2.maxOf { it.value } - c2.minOf { it.value }
    }
}