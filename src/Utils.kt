import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String,separator: String = "\n") = Path("src/$name.txt").readText().trim().split(separator)


/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun <T> performTest(
    testFileName: String,
    actualFileName: String,
    part1: (List<String>) -> T,
    part2: (List<String>) -> T,
    expectedPart1: T,
    expectedPart2: T,
    separator: String = "\n"
) {
    val testInput = readInput(testFileName,separator)
    val part1 = part1(testInput)
    val part2 = part2(testInput)
    println("Part1: expected: $expectedPart1, got: $part1")
    println(part1 == expectedPart1)
    check(part1 == expectedPart1)
    println("Part2: expected: $expectedPart2, got: $part2")
    check(part2 == expectedPart2)
    val input = readInput(actualFileName, separator)
    part1(input).println()
    part2(input).println()

}

fun String.splitAtIndex(index: Int) = require(index in 0..length).let {
    take(index) to substring(index)
}