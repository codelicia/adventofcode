package com.codelicia.advent2021

class Day16(val input: String) {

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
        val operator: Int?,
        val biteLength: Int,
        val literal: Int?,
        val subPackets: List<Packet>?,
        val groups: String
    ) {
        companion object {
            private fun String.decodeBinary(): Int = this.toByte(2).toInt()

            fun of(bitsStreaming: String): Packet {
                // the following 3 bits are the type ID
                val typeID: Int = bitsStreaming.take(6).takeLast(3).decodeBinary()

                if (typeID == 4)  {
                    return Packet(
                        // first three bits are the packet version
                        bitsStreaming.take(3).decodeBinary(),
                        typeID,
                        null,
                        5,
                        10,
                        listOf(),
                        bitsStreaming.takeLast(bitsStreaming.length - 6)
                    )
                }

                // 15
                if (bitsStreaming.take(7).takeLast(1).toInt() == 0) {
                    val bits = bitsStreaming.take(7 + 15).takeLast(15).decodeBinary()
                    val subPackets = bitsStreaming.takeLast(bitsStreaming.length - (7 + 15)).take(bits)


                    var ll = mutableListOf<Packet>()
                    var binaryString = ""
                    var lastGroup = false
                    var skipAll = false
                    subPackets.forEachIndexed { i, c ->
                        if (i != 0 && i % 11 == 0) {
                            ll.add(Packet.of(binaryString))
                            if (lastGroup) skipAll = true
                            if (c == '0') lastGroup = true
                            return@forEachIndexed
                        }

                        if (skipAll) return@forEachIndexed
                        binaryString += c
                    }

                    val p = Packet(
                        // first three bits are the packet version
                        bitsStreaming.take(3).decodeBinary(),
                        // the following 3 bits are the type ID
                        bitsStreaming.take(6).takeLast(3).decodeBinary(),
                        bitsStreaming.take(7).takeLast(1).toInt(),
                        bitsStreaming.take(7 + 15).takeLast(15).decodeBinary(),
                        null,
                        ll,
                        subPackets
                    )

                    return p
                }

                // 11 - number of sub-packages

                val bits = bitsStreaming.take(7 + 11).takeLast(11).decodeBinary()

                val p = Packet(
                    // first three bits are the packet version
                    bitsStreaming.take(3).decodeBinary(),
                    // the following 3 bits are the type ID
                    bitsStreaming.take(6).takeLast(3).decodeBinary(),
                    bitsStreaming.take(7 + 11).takeLast(11).decodeBinary(),
                    if (bitsStreaming.take(7).takeLast(1).toInt() == 0) 15 else 11,
                    null,
                    listOf(Packet.of(bitsStreaming.takeLast(bitsStreaming.length - bits))),
                    bitsStreaming.takeLast(bitsStreaming.length - 7)
                )

                return p
            }
        }
    }

    fun part1(): Int {

        val bitsStreaming = input.map { c -> lookUp[c] }.joinToString("")

        val p = Packet.of(bitsStreaming)

        println(p)

        var binaryString = ""
        var lastGroup = false
        var skipAll = false
        p.groups.forEachIndexed { i, c ->
            if (i == 0 || i % p.biteLength == 0) {
                if (lastGroup) skipAll = true
                if (c == '0') lastGroup = true
                return@forEachIndexed
            }

            if (skipAll) return@forEachIndexed
            binaryString += c
        }

        return binaryString.toInt(2)
    }
}