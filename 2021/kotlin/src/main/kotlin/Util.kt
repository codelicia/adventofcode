package Util

import java.io.File

fun readInput(day: Int): String = File("input/input-$day.txt").readText()
fun readInputAsList(day: Int): List<String> = File("input/input-$day.txt").readText().split("\n")
fun readInputAsIntList(day: Int): List<Int> = File("input/input-$day.txt").readText().split(",").map { it.toInt() }
fun readExample(day: Int): String = File("input/example-$day.txt").readText()
