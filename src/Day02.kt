private val regexPart1 = "(^\\d+)(\\1)$".toRegex()
private val regexPart2 = "^(\\d+)(\\1)+$".toRegex()

fun main() {
  fun part1(input: List<String>): Long {
    return input
        .map { line -> line.split("-") }
        .sumOf { (start, end) ->
          var count = 0L
          for (i in start.toLong()..end.toLong()) {
            println("Current $i")
            val iString = i.toString()
            if (iString.matches(regexPart1)) {
              count += i
              println("INVALID")
            }
          }
          count
        }
  }

  fun part2(input: List<String>): Long {
    return input
        .map { line -> line.split("-") }
        .sumOf { (start, end) ->
          var count = 0L
          for (i in start.toLong()..end.toLong()) {
            println("Current $i")
            val iString = i.toString()
            if (iString.matches(regexPart2)) {
              count += i
              println("INVALID")
            }
          }
          count
        }
  }

  performTest(
      testFileName = "Day02_test",
      actualFileName = "Day02",
      part1 = ::part1,
      part2 = ::part2,
      expectedPart1 = 1227775554L,
      expectedPart2 = 4174379265L,
      separator = ",",
  )
}
