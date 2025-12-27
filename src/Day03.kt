private fun combine(a: Long, b: Long): Long {
  return a * 10 + b
}

fun main() {
  fun part1(input: List<String>): Long {
    return input.sumOf {
      val digits = it.split("").filterNot { it.isBlank() }.map { it.toLong() }
      val max = digits.max()
      val maxIdx = digits.indexOf(max)
      val before = digits.take(maxIdx)
      val after = digits.slice(maxIdx + 1 until digits.size)
      var beforeMax = before.maxOrNull()
      if (beforeMax == null) {
        beforeMax = 0
      } else {
        beforeMax = combine(beforeMax, max)
      }
      var afterMax = after.maxOrNull()
      if (afterMax == null) {
        afterMax = max
      } else {
        afterMax = combine(max, afterMax)
      }
      maxOf(beforeMax, afterMax)
    }
  }

  fun part2(input: List<String>): Long {
    return input.sumOf {
      var line = it
      val chars = mutableListOf<Char>()
      for (i in 0..<12) {
        var left = 0
        for (j in 1..<(line.length - (12 - i) + 1)) {
          if (line[j] > line[left]) {
            left = j
          }
        }
        chars.add(line[left])
        line = line.slice(left + 1..<line.length)
      }
      String(chars.toCharArray()).toLong()
    }
  }

  performTest(
      fileName = "Day03",
      part1 = ::part1,
      part2 = ::part2,
      expectedPart1 = 357L,
      expectedPart2 = 3121910778619L,
  )
}
