package com.codelicia.advent2021

class Day16(val input: String) {

    private fun hexToBinary(input: String): List<Char> =
        input.map { it.digitToInt(16).toString(2).padStart(4, '0') }.flatMap { it.toList() }

    private var transmission: Iterator<Char> = hexToBinary(input).iterator()

    fun Iterator<Char>.next(size: Int): String =
        (1..size).map { next() }.joinToString("")

    fun part1(): Long = getPacket(transmission).first

    fun part2(): Long = getPacket(transmission).second

    // Version -> Count
    private fun getPacket(transmission: Iterator<Char>): Pair<Long, Long> {
        val subPackets = mutableListOf<Long>()

        val version = transmission.next(3).toInt(2)
        val typeId = transmission.next(3).toInt(2)

        var versionSum = version.toLong()

        when (typeId) {
            4 -> {
                var keepCounting = true;
                var count = ""
                while (keepCounting) {
                    val lastBit = transmission.next(1)
                    if (lastBit == "0") keepCounting = false;

                    val rest = transmission.next(4)
                    count += rest
                }
                subPackets.add(count.toLong(2))
            }

            else -> {
                val type = transmission.next(1).toInt(2)
                when (type) {
                    0 -> { // 15
                        val subPacketsLength = transmission.next(15).toInt(2)

                        val subPacketBits = transmission.next(subPacketsLength).toCharArray()
                        val iterator = subPacketBits.iterator()

                        while (iterator.hasNext()) {
                            val p = getPacket(iterator)
                            versionSum += p.first
                            subPackets.add(p.second)
                        }
                        return versionSum to subPackets.operateBy(typeId)
                    }
                    1 -> { // 11
                        val subPacketsLength = transmission.next(11).toInt(2)

                        repeat (subPacketsLength) {
                            val p = getPacket(transmission)
                            versionSum += p.first
                            subPackets.add(p.second)
                        }
                        return versionSum to subPackets.operateBy(typeId)
                    }
                }
            }
        }

        return versionSum to subPackets.operateBy(typeId)
    }

    fun MutableList<Long>.operateBy(id: Int) =
        when (id) {
            0 -> sumOf { it }
            1 -> reduce { acc, cur -> acc * cur }
            2 -> minOf { it }
            3 -> maxOf { it }
            5 -> if (this[0] > this[1]) 1 else 0
            6 -> if (this[0] < this[1]) 1 else 0
            7 -> if (this[0] == this[1]) 1 else 0
            else -> first()
        }
}