package com.codelicia.advent2021

import java.util.Stack

class Day12(mapOfTheRemainingCaves: List<String>) {

    data class Vertex(val label: String)

    private val map = mapOfTheRemainingCaves
        .map { cave -> cave.split("-") }
        .map { cave -> cave.first() to cave.last() }

    private val graph = buildMap<Vertex, MutableList<Vertex>> {
        for (i in map) {
            this.putIfAbsent(Vertex(i.first), mutableListOf())
            this.putIfAbsent(Vertex(i.second), mutableListOf())
            this[Vertex(i.first)]!!.add(Vertex(i.second))
            this[Vertex(i.second)]!!.add(Vertex(i.first))
        }
    }

    fun part1(): Int = solve(fun(_) = true)

    fun part2(): Int {
        return solve(fun(visited: HashMap<String, Int>): Boolean {
            return visited.toMap().filter { it.value > 1 }.filter { it.key.lowercase() == it.key }.values.isNotEmpty()
        })
    }

    private fun solve(predicate: (HashMap<String, Int>) -> Boolean): Int {
        val queue = Stack<Vertex>()
        queue.add(Vertex("start"))

        val results = mutableListOf<String>()

        while (queue.isNotEmpty()) bfs(graph, predicate, queue, results, "start")

        return results.size
    }

    private fun bfs(
        graph: Map<Vertex, MutableList<Vertex>>,
        predicate: (HashMap<String, Int>) -> Boolean,
        queue: Stack<Vertex>,
        results: MutableList<String>,
        path: String,
        visited: HashMap<String, Int> = hashMapOf()
    ) {
        val vertex = queue.pop()
        if (vertex.label == "end") {
            results.add(path)
            return
        }

        val count = visited.getOrElse(vertex.label) { 0 }
        visited[vertex.label] = count + 1

        val neighbours = graph[vertex]!!
        for (neighbour in neighbours) {
            if (neighbour.label != "start") {
                val satisfiesPredicate = predicate(visited)

                if (neighbour.label.lowercase() == neighbour.label && visited.contains(neighbour.label) && satisfiesPredicate == true) continue

                queue.add(neighbour)
                bfs(graph, predicate, queue, results, "$path,${neighbour.label}", HashMap(visited))
            }
        }
    }
}