package com.codelicia.advent2021

class Day14(val input: String) {
    fun part1(): Int {
        var polymerTemplate = input.split("\n\n").take(1).first().trim().split("").filterNot { it.isBlank() }

        // Mount hashMap
        val hashMap = HashMap<String, String>()
        input.split("\n\n").drop(1).first().split("\n")
            .map { it.split(" -> ") }
            .map { hashMap[it.first()] = it.last() }

        val memo = HashMap<String, String>()

        var x = ""
        repeat(40) {
            println(it)
            val windowed = polymerTemplate.windowed(2, 1, true)
            x = windowed
                .foldIndexed(" ") { index, acc, s ->
                    val key = windowed[index].joinToString(separator = "")
                    if (memo.get(key) == null) {
                        memo.set(key, windowed[index].first() + (hashMap.get(s.joinToString(separator = "")) ?: ""))
                    }

                    acc + memo.get(key)
                }
            polymerTemplate = x.split("").filterNot { it.isBlank() }
        }
        println(x)

        val groups = x.split("").filterNot { it.isBlank() }.groupingBy { it }.eachCount()

        return groups.maxOf { it.value } - groups.minOf { it.value }
    }
}