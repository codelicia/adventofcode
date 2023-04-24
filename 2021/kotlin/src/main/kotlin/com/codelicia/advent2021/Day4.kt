package com.codelicia.advent2021

class Day4(input: String) {
    private val ON_COMMA = ","
    private val ON_NEW_LINE = "\n"
    private val ON_SECTION = "\n\n"

    private val inputSections = input.split(ON_SECTION)

    private val calledNumbers = inputSections[0].trim().split(ON_COMMA).map(String::toInt)

    private var bingoCards = buildList<BingoCard> {

        for (i in 1 until inputSections.size) {
            val numbers =
                inputSections[i]
                    .split(ON_NEW_LINE)
                    .map {
                        it.split(" ")
                            .filter(String::isNotBlank)
                            .map(String::toInt)
                    }

            val xs = numbers.map { row ->
                row.map { number ->
                    Pair(number, false)
                }
            }

            add(BingoCard(xs))
        }
    }

    class BingoCard(var numbers: List<List<Pair<Int, Boolean>>>) {

        private fun hasNumber(number: Int): Boolean {
            return numbers.flatten()
                .map { it.first }
                .contains(number)
        }

        fun markNumber(number: Int) {

            if (false == hasNumber(number)) {
                return
            }

            numbers = numbers.map {
                it.map { pair ->
                    if (pair.first == number) {
                        Pair(pair.first, true)
                    } else {
                        pair
                    }
                }
            }
        }

        fun score(): Int {
            return numbers.flatten()
                .filter { it.second == false }
                .map { it.first }
                .sum()
        }

        fun isBingo(): Boolean {
            numbers.forEach { row ->
                row.forEach { number ->
                    if (row.filter { it.second == true }.count() == row.size) return true
                }
            }

            for (i in 0 until numbers[0].size) {
                val column = mutableListOf<Pair<Int, Boolean>>()
                for (j in 0 until numbers[0].size) {
                    column.add(numbers[j][i])
                }

                if (column.filter { it.second == true }.count() == numbers[0].size) return true
            }

            return false
        }
    }

    fun part1(): Int {

        calledNumbers.forEachIndexed { index, numberCalled ->
            println("Round ${index}")
            bingoCards.forEach { card ->
                card.markNumber(numberCalled)
            }

            val isBingo = bingoCards.filter { it.isBingo() }

            if (isBingo.size > 0) {
                println("Bingooo!!!!")
                println("bingo.size = ${isBingo.size}")
                println("bingo = ${isBingo[0].numbers}")
                return isBingo[0].score() * numberCalled
            }
        }

        // @todo only for debugging
        bingoCards.forEach { card ->
            println(card.numbers)
        }
        return 1
    }
}