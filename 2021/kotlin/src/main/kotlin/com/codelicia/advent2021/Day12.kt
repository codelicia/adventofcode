package com.codelicia.advent2021

import java.util.Stack

class Day12(mapOfTheRemainingCaves: List<String>) {

    data class Vertex(val label: String)

    private val graph: MutableMap<Vertex, MutableList<Vertex>> = mutableMapOf()

    // @TODO: Maybe build the tree here already?
    private val map = mapOfTheRemainingCaves
        .map { it.split("-") }
        .map { it[0] to it[1] }

    fun part1(): Int {
        // Build cave connections
        for (i in map) {
            if (graph[Vertex(i.first)] == null)
                graph.putIfAbsent(Vertex(i.first), mutableListOf(Vertex(i.second)))
            else
                graph[Vertex(i.first)]?.add(Vertex(i.second))

            if (graph[Vertex(i.second)] == null)
                graph.putIfAbsent(Vertex(i.second), mutableListOf(Vertex(i.first)))
            else
                graph[Vertex(i.second)]?.add(Vertex(i.first))
        }

        val queue = Stack<Vertex>()
        queue.add(Vertex("start"))
        val visited = mutableSetOf<String>()

        val results = mutableListOf<String>()
        while (queue.isNotEmpty()) {
            go(graph, queue, results, "start", visited)
        }
        return results.size
    }

    fun part2(): Int {
        // Build cave connections
        for (i in map) {
            if (graph[Vertex(i.first)] == null)
                graph.putIfAbsent(Vertex(i.first), mutableListOf(Vertex(i.second)))
            else
                graph[Vertex(i.first)]?.add(Vertex(i.second))

            if (graph[Vertex(i.second)] == null)
                graph.putIfAbsent(Vertex(i.second), mutableListOf(Vertex(i.first)))
            else
                graph[Vertex(i.second)]?.add(Vertex(i.first))
        }

        val queue = Stack<Vertex>()
        queue.add(Vertex("start"))
        val visited = hashMapOf<String, Int>()

        val results = mutableListOf<String>()
        while (queue.isNotEmpty()) {
            go2(graph, queue, results, "start", visited)
        }
        return results.size
    }

    private fun go2(
        graph: MutableMap<Vertex, MutableList<Vertex>>,
        queue: Stack<Vertex>,
        results: MutableList<String>,
        result: String,
        visited: HashMap<String, Int>
    ) {
        val v = queue.pop()
        if (v.label == "end") {
            results.add(result)
            return
        }

        val x = visited.getOrElse(v.label) { 0 }
        visited[v.label] = x + 1

        val vertices = graph[v]!!
        for (i in vertices) {
            if (i.label != "start") {
                val twice = visited.toMap().filter { it.value > 1 }.filter { it.key.lowercase() == it.key }.values

                val hasVisitedSmallCaveTwice = twice.isNotEmpty()

                if (i.label.lowercase() == i.label && visited.contains(i.label) && hasVisitedSmallCaveTwice == true) continue

                queue.add(i)
                go2(graph, queue, results, "$result,${i.label}", HashMap(visited))
            }
        }
    }

    private fun go(
        graph: MutableMap<Vertex, MutableList<Vertex>>,
        queue: Stack<Vertex>,
        results: MutableList<String>,
        result: String,
        visited: MutableSet<String>
    ) {
        val v = queue.pop()
        if (v.label == "end") {
            results.add(result)
            return
        }

        visited.add(v.label)

        val vertices = graph[v]!!
        for (i in vertices) {
            if (i.label != "start") {
                if (i.label.lowercase() == i.label && visited.contains(i.label)) continue

                queue.add(i)
                go(graph, queue, results, "$result,${i.label}", visited.toMutableSet())
            }
        }
    }
}