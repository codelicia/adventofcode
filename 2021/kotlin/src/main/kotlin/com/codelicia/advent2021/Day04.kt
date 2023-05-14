package com.codelicia.advent2021

class Day04(input: String) {
    private val newline = "\n"
    private val section = "\n\n"

    private val sections = input.split(section)

    private val draws = sections[0].trim().split(",").map(String::toInt)

    private val cards : List<BingoBoard> = buildList {
        for (i in 1 until sections.size) {
            val numbers = sections[i].split(newline).map { it.split(" ").filter(String::isNotBlank).map(String::toInt) }

            add(BingoBoard(numbers.map { row -> row.map { it to false } }))
        }
    }

    class BingoBoard(private var numbers: List<List<Pair<Int, Boolean>>>) {

        fun mark(number: Int) {
            numbers = numbers.map {
                it.map { pair ->
                    if (pair.first == number) pair.first to true else pair
                }
            }
        }

        fun unmarkedSum(): Int = numbers.flatten().filter { !it.second }.sumOf { it.first }

        fun hasBingo(): Boolean {
            val diagonal = List(numbers[0].size) { column -> List(numbers[0].size) { numbers[it][column] } }
            val merge = numbers + diagonal

           merge.forEach { row ->
                row.forEach { _ ->
                    if (row.count { it.second } == row.size) return true
                }
            }

            return false
        }
    }

    private fun cardsScore(): MutableSet<Pair<Int, Int>> {
        val winningCards = mutableSetOf<Pair<Int, Int>>()

        draws.forEach { numberCalled ->
            cards.forEach { it.mark(numberCalled) }

            val isBingo = cards.filter { it.hasBingo() }

            isBingo.forEach { card ->
                if (false == winningCards.map { it.first }.contains(card.hashCode())) {
                    winningCards.add(card.hashCode() to numberCalled * card.unmarkedSum())
                }
            }
        }
        return winningCards
    }

    fun part1(): Int = cardsScore().first().second

    fun part2(): Int = cardsScore().last().second
}