package com.codelicia.advent2021

import java.util.Stack

class Day12(mapOfTheRemainingCaves: List<String>) {

    data class Vertex(val label: String)

    // @TODO: Maybe build the tree here already?
    private val map = mapOfTheRemainingCaves
        .map { it.split("-") }
        .map { it[0] to it[1] }

    private val graph = buildMap<Vertex, MutableList<Vertex>> {
        for (i in map) {
            if (this[Vertex(i.first)] == null)
                this.putIfAbsent(Vertex(i.first), mutableListOf(Vertex(i.second)))
            else
                this[Vertex(i.first)]?.add(Vertex(i.second))

            if (this[Vertex(i.second)] == null)
                this.putIfAbsent(Vertex(i.second), mutableListOf(Vertex(i.first)))
            else
                this[Vertex(i.second)]?.add(Vertex(i.first))
        }
    }

    fun part1(): Int {
        val queue = Stack<Vertex>()
        queue.add(Vertex("start"))
        val visited = hashMapOf<String, Int>()

        val results = mutableListOf<String>()
        while (queue.isNotEmpty()) {
            bfs(graph, fun(_: HashMap<String, Int>): Boolean = true, queue, results, "start", visited)
        }
        return results.size
    }

    fun part2(): Int {
        val queue = Stack<Vertex>()
        queue.add(Vertex("start"))
        val visited = hashMapOf<String, Int>()

        val results = mutableListOf<String>()
        val predicate = fun(visited: HashMap<String, Int>): Boolean {
            return visited.toMap().filter { it.value > 1 }.filter { it.key.lowercase() == it.key }.values.isNotEmpty()
        }
        while (queue.isNotEmpty()) {
            bfs(graph, predicate, queue, results, "start", visited)
        }
        return results.size
    }

    private fun bfs(
        graph: Map<Vertex, MutableList<Vertex>>,
        predicate: (HashMap<String, Int>) -> Boolean,
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
                val satisfiesPredicate = predicate(visited)

                if (i.label.lowercase() == i.label && visited.contains(i.label) && satisfiesPredicate == true) continue

                queue.add(i)
                bfs(graph, predicate, queue, results, "$result,${i.label}", HashMap(visited))
            }
        }
    }
}