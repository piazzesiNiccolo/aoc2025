fun main() {
  fun part1(input: List<String>): Long {
    var count = 0L
    val beams = mutableSetOf(input.first().indexOfFirst { it == 'S' })
    val splits = input.slice(1..input.lastIndex).parseCoordinates("^").sortedBy { it.x }.toList()
    for (split in splits) {
      val toAdd = mutableSetOf<Int>()
      val toRemove = mutableSetOf<Int>()
      for (beam in beams) {
        if (beam == split.y) {
          count++
          toAdd.add(beam - 1)
          toAdd.add(beam + 1)
          toRemove.add(beam)
        }
      }
      if (toAdd.isNotEmpty()) {
        beams.removeAll(toRemove)
        beams.addAll(toAdd)
      }
    }
    return count
  }

  fun part2(input: List<String>): Long {
    var beams = mutableMapOf(input.first().indexOfFirst { it == 'S' } to 1L).withDefault { 0L }
    for (line in input) {
      val newMap = mutableMapOf<Int, Long>().withDefault { 0L }
      for ((beam, count) in beams) {
        if (line[beam] == '^') {
          newMap[beam - 1] = newMap.getValue(beam - 1).plus(count)
          newMap[beam + 1] = newMap.getValue(beam + 1).plus(count)
        } else {
          newMap[beam] = newMap.getValue(beam) + count
        }
      }
      beams = newMap
    }
    return beams.values.sum()
  }

  performTest(
      fileName = "Day07",
      part1 = ::part1,
      part2 = ::part2,
      expectedPart1 = 21L,
      expectedPart2 = 40L,
  )
}
