package com.codelicia.advent2021

class Day4(input: String) {
    private val ON_COMMA = ","
    private val ON_NEW_LINE = "\n"
    private val IN_SECTION = "\n\n"

    private val sections = input.split(IN_SECTION)

    private val draws = sections[0].trim().split(ON_COMMA).map(String::toInt)

    private var bingoCards : List<BingoCard> = buildList {
        for (i in 1 until sections.size) {
            val numbers = sections[i].split(ON_NEW_LINE).map { it.split(" ").filter(String::isNotBlank).map(String::toInt) }

            add(BingoCard(numbers.map { row -> row.map { it to false } }))
        }
    }

    class BingoCard(private var numbers: List<List<Pair<Int, Boolean>>>) {

        fun markNumber(number: Int) {
            numbers = numbers.map {
                it.map { pair ->
                    if (pair.first == number) pair.first to true else pair
                }
            }
        }

        fun score(): Int = numbers.flatten().filter { !it.second }.sumOf { it.first }

        fun isBingo(): Boolean {
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

    fun part1(): Int {

        draws.forEach { numberCalled ->
            bingoCards.forEach { it.markNumber(numberCalled) }

            val isBingo = bingoCards.filter { it.isBingo() }

            if (isBingo.isNotEmpty()) return isBingo[0].score() * numberCalled
        }

        error("No bingo found")
    }

    fun part2(): Int {
        val bingoHistoric = mutableSetOf<Pair<Int, Int>>()

        draws.forEach { numberCalled ->
            bingoCards.forEach { it.markNumber(numberCalled) }

            val isBingo = bingoCards.filter { it.isBingo() }

            isBingo.forEach { card ->
                if (false == bingoHistoric.map { it.first }.contains(card.hashCode())) {
                    bingoHistoric.add(card.hashCode() to numberCalled * card.score())
                }
            }
        }

        // TODO: If we change `last()` to `first()` we can solve part 1 as well with the same logic
        return bingoHistoric.last().second
    }
}