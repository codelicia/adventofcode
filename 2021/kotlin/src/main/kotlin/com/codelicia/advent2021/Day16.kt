package com.codelicia.advent2021

import java.util.*
import kotlin.math.min

class Day16(val input: String) {

    private fun String.decodeBinary(): Int = this.toByte(2).toInt()

    // @TODO("Can I get rid of this table")
    private val lookUp: Map<Char, String> = mutableMapOf(
       '0' to "0000",
       '1' to "0001",
       '2' to "0010",
       '3' to "0011",
       '4' to "0100",
       '5' to "0101",
       '6' to "0110",
       '7' to "0111",
       '8' to "1000",
       '9' to "1001",
       'A' to "1010",
       'B' to "1011",
       'C' to "1100",
       'D' to "1101",
       'E' to "1110",
       'F' to "1111",
    )

    data class Packet(
        val version: Int,
        val typeID: Int,
        val groups: String,
        val lastBits: String
    )

    fun part1(): Int {

        val bitsStreaming = input.map { c -> lookUp[c] }.joinToString("")

        // @todo probably use slide window here
        val message = bitsStreaming.filterIndexed { index, c ->
            if (index < 6 || index > bitsStreaming.length - 4) return@filterIndexed false;
            return@filterIndexed true
        }

        val p = Packet(
            bitsStreaming.take(3).decodeBinary(),
            bitsStreaming.take(6).takeLast(3).decodeBinary(),
            bitsStreaming.takeLast(bitsStreaming.length - 6),
            bitsStreaming.take(3),
        )

        println(p)


        return 2022
    }
}