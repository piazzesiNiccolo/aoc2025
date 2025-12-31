import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.pow
import kotlin.math.sqrt

/** Reads lines from the given input txt file. */
fun readInput(name: String, separator: String = "\n") =
    Path("src/$name.txt").readText().trim().split(separator)

/** Converts string to md5 hash. */
fun String.md5() =
    BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
        .toString(16)
        .padStart(32, '0')

/** The cleaner shorthand for printing output. */
fun Any?.println() = println(this)

fun <T> performTest(
    fileName: String,
    part1: (List<String>) -> T,
    part2: (List<String>) -> T,
    expectedPart1: T,
    expectedPart2: T,
    separator: String = "\n",
) {
  val testInput = readInput("${fileName}_test", separator)
  val part1 = part1(testInput)
  val part2 = part2(testInput)
  println("Part1: expected: $expectedPart1, got: $part1")
  check(part1 == expectedPart1)
  println("Part2: expected: $expectedPart2, got: $part2")
  check(part2 == expectedPart2)
  val input = readInput(fileName, separator)
  part1(input).println()
  part2(input).println()
}

// ==== Graph like utils ====

data class Coordinate2D(val x: Int, val y: Int)

data class Coordinate3D(val x: Int, val y: Int, val z: Int) {
  companion object {
    fun fromString(coordinate3D: String): Coordinate3D {
      val (x, y, z) = coordinate3D.split(",")
      return Coordinate3D(x.toInt(), y.toInt(), z.toInt())
    }
  }
}

fun Coordinate2D.euclideanDistance(other: Coordinate2D) =
    sqrt((this.x - other.x).toDouble().pow(2) + (this.y - other.y).toDouble().pow(2))

fun Coordinate3D.euclideanDistance(other: Coordinate3D) =
    sqrt(
        (this.x - other.x).toDouble().pow(2) +
            (this.y - other.y).toDouble().pow(2) +
            (this.z - other.z).toDouble().pow(2)
    )

fun List<String>.parseCoordinates(marker: String): MutableSet<Coordinate2D> {
  val set = mutableSetOf<Coordinate2D>()
  for ((i, s) in this.withIndex()) {
    for ((j, c) in s.withIndex()) {
      if (c.toString() == marker) {
        set.add(Coordinate2D(i, j))
      }
    }
  }
  return set
}

fun Coordinate2D.adjacent8() = iterator {
  val (x, y) = this@adjacent8.x to this@adjacent8.y
  for (i in (-1..1)) {
    for (j in (-1..1)) {
      if (i == 0 && j == 0) {
        continue
      }
      yield(Coordinate2D(x + i, y + j))
    }
  }
}

// == Interval utilities

fun <T : Comparable<T>> List<Pair<T, T>>.mergeOverlapping(): List<Pair<T, T>> {
  var res = mutableListOf<Pair<T, T>>()
  val sorted = this.sortedBy { it.first }
  res.add(sorted[0])
  for (other in sorted.slice(1..<sorted.size)) {
    if (res.last().second >= other.first) {
      res[res.lastIndex] = res[res.lastIndex].first to maxOf(res.last().second, other.second)
    } else {
      res.add(other)
    }
  }
  return res
}

fun <T> List<T>.allPairs(): List<Pair<T, T>> = flatMapIndexed { i, a ->
  drop(i + 1).map { b -> a to b }
}
