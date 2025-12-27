fun main() {
  fun part1(input: List<String>): Long {
    var count = 0L
    val (rangesString, ingredientsString) = input
    val ranges =
        rangesString
            .split("\n")
            .map {
              val (a, b) = it.split("-")
              a.toLong() to b.toLong()
            }
            .sortedWith(
                Comparator.comparingLong<Pair<Long, Long>> { it.first }.thenComparing { it.second }
            )
            .toList()
    val ingredients = ingredientsString.split("\n").map { it.toLong() }
    for (ingredient in ingredients) {
      val filteredRange = ranges.filterNot { it.second < ingredient || it.first > ingredient }
      for (r in filteredRange) {
        if (r.first <= ingredient && ingredient <= r.second) {
          count++
          break
        }
      }
    }
    return count
  }

  fun part2(input: List<String>): Long {
    var count = 0L
    val (rangesString, _) = input
    val ranges =
        rangesString.split("\n").map {
          val (a, b) = it.split("-")
          a.toLong() to b.toLong()
        }
    for (r in ranges.mergeOverlapping()) {
      count += r.second - (r.first - 1)
    }
    return count
  }

  performTest(
      fileName = "Day05",
      part1 = ::part1,
      part2 = ::part2,
      expectedPart1 = 3L,
      expectedPart2 = 14L,
      separator = "\n\n",
  )
}
