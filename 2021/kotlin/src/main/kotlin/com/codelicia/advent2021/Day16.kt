package com.codelicia.advent2021

class Day16(val input: String) {

    private fun hexToBinary(input: String): List<Char> =
        input.map { it.digitToInt(16).toString(2).padStart(4, '0') }.flatMap { it.toList() }

    private var transmission: Iterator<Char> = hexToBinary(input).iterator()

    fun Iterator<Char>.next(size: Int): String =
        (1..size).map { next() }.joinToString("")

    fun part1(): Pair<Long, Long> = getPacket(transmission)

    // Version -> Count
    private fun getPacket(transmission: Iterator<Char>): Pair<Long, Long> {
        val version = transmission.next(3).toInt(2)
        val typeId = transmission.next(3).toInt(2)

        var versionSum = version.toLong()
        var countSum = 0L

        var count = ""
        when (typeId) {
            4 -> {
                var keepCounting = true;
                while (keepCounting) {
                    val lastBit = transmission.next(1)
                    if (lastBit == "0") keepCounting = false;

                    val rest = transmission.next(4)
                    count += rest
                }
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
                            countSum += p.second
                            versionSum += p.first
                        }
                        return versionSum to countSum
                    }
                    1 -> { // 11
                        val subPacketsLength = transmission.next(11).toInt(2)

                        repeat (subPacketsLength) {
                            val p = getPacket(transmission)
                            versionSum += p.first
                            countSum += p.second
                        }
                        return versionSum to countSum
                    }
                }
            }
        }

        return versionSum to countSum + count.toLong(2)
    }
}