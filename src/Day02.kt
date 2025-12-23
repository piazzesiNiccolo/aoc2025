

fun main() {
    fun part1(input: List<String>): Int {
        var count = 0
        return count
    }

    fun part2(input: List<String>): Int {
        var current = 50
        var count = 0
        return count
    }


    // Or read a large test input from the `src/Day01_test.txt` file:
    performTest(
        fileName = "Day02_test",
        part1 = ::part1,
        part2 = ::part2,
        expectedPart1 = 0,
        expectedPart2 = 0
    )
    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

