fun main() {
  fun part1(input: List<String>) =
      input.map(Coordinate2D::fromString).allPairs().maxOfOrNull {
        it.first.rectangleArea(it.second)
      } ?: 0L

  fun part2(input: List<String>): Long {
    return 0L
  }
  performTest(
      fileName = "Day09",
      part1 = ::part1,
      part2 = ::part2,
      expectedPart1 = 50L,
      expectedPart2 = 0L,
  )
}
