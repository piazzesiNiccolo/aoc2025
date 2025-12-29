fun main() {
  fun part1(input: List<String>): Long {
    val opsStrings = input.last().split(" ").filter { it.isNotEmpty() }
    val count = LongArray(opsStrings.size) { 0L }
    val ops = arrayOfNulls<(Long, Long) -> Long>(opsStrings.size)
    val add = { i1: Long, i2: Long -> i2 + i1 }
    val mult = { i1: Long, i2: Long -> i2 * i1 }
    for ((i, op) in opsStrings.withIndex()) {
      if (op == "*") {
        ops[i] = mult
        count[i] = 1
      } else if (op == "+") {
        ops[i] = add
      }
    }
    for (line in input.subList(0, input.size - 1)) {
      for ((i, n) in line.split(" ").filter { it.isNotEmpty() }.withIndex()) {
        val op = ops[i] ?: { a, b -> error("Invalid op ") }
        count[i] = op(count[i], n.toLong())
      }
    }

    return count.sum()
  }

  fun part2(input: List<String>): Long {
    var count = 0L
    return count
  }

  performTest(
      fileName = "Day06",
      part1 = ::part1,
      part2 = ::part2,
      expectedPart1 = 4277556L,
      expectedPart2 = 0L,
  )
}
