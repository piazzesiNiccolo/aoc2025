private const val MOD = 100

private enum class Direction {
  Left,
  Right,
}

private fun Int.positiveModulo(mod: Int): Int {
  val result = this % mod
  return if (result < 0) result + mod else result
}

private data class Iteration(val direction: Direction, val value: Int)

private fun parseDirection(line: String): Iteration =
    if (line.startsWith("R")) Iteration(Direction.Right, line.substring(1).toInt())
    else Iteration(direction = Direction.Left, value = line.substring(1).toInt())

fun main() {
  fun part1(input: List<String>): Int {
    var current = 50
    var count = 0
    input.map(::parseDirection).forEach { dir ->
      current =
          when (dir.direction) {
            Direction.Left -> (current - dir.value).positiveModulo(MOD)
            Direction.Right -> (current + dir.value).positiveModulo(MOD)
          }
      if (current == 0) count++
    }
    return count
  }

  fun part2(input: List<String>): Int {
    var current = 50
    var count = 0
    input.map(::parseDirection).forEach { dir ->
      val mod = dir.value % MOD
      val iterations = dir.value / MOD
      count += iterations
      if (mod != 0) {
        val prev = current
        val op =
            when (dir.direction) {
              Direction.Right -> { x: Int, y: Int -> x + y }
              Direction.Left -> { x: Int, y: Int -> x - y }
            }
        current = op(current, mod)
        if (current <= 0 && 0 < prev && dir.direction == Direction.Left) count++
        if (current >= MOD && dir.direction == Direction.Right) count++
        current = current.positiveModulo(MOD)
      }
    }
    return count
  }

  // Or read a large test input from the `src/Day01_test.txt` file:
  performTest(
      testFileName = "Day01_test",
      actualFileName = "Day01",
      part1 = ::part1,
      part2 = ::part2,
      expectedPart1 = 3,
      expectedPart2 = 6,
  )

  // Read the input from the `src/Day01.txt` file.
  val input = readInput("Day01")
  part1(input).println()
  part2(input).println()
}
