fun main() {
  fun part1(input: List<String>): Long {
    val coords = input.parseCoordinates("@")
    return coords
        .map { coord ->
          var count = 0
          val adj = coord.adjacent8()
          for (a in adj) {
            if (a in coords) count++
          }
          count
        }
        .count { it < 4 }
        .toLong()
  }

  fun part2(input: List<String>): Long {
    var current = input.parseCoordinates("@")
    var total = 0L
    var removed = 1
    while (removed != 0) {
      val toRemove =
          current.filter { coord ->
            var count = 0
            val adj = coord.adjacent8()
            for (a in adj) {
              if (a in current) count++
            }
            count < 4
          }
      total += toRemove.size
      removed = toRemove.size
      current.removeAll(toRemove)
    }
    return total
  }

  performTest(
      fileName = "Day04",
      part1 = ::part1,
      part2 = ::part2,
      expectedPart1 = 13L,
      expectedPart2 = 43L,
  )
}
