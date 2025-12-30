fun main() {
  fun part1(input: List<String>): Long {
    val add = { i1: Long, i2: Long -> i2 + i1 }
    val mult = { i1: Long, i2: Long -> i2 * i1 }
    val opsStrings = input.last().split(" ").filter { it.isNotEmpty() }
    val count = LongArray(opsStrings.size) { 0L }
    val ops = arrayOfNulls<(Long, Long) -> Long>(opsStrings.size)
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

    val add = List<Long>::sum
    val mult = { input: List<Long> -> input.fold(1L) { a, b -> a * b } }
    var count = 0L
    val r = Regex("([+*])( *)")
    val lines = input.subList(0, input.size - 1)
    val opString = input.last()
    var offset = 0
    val numStrs = mutableListOf<Pair<List<String>, (List<Long>) -> Long>>()
    while (offset < opString.length) {
      val match = r.matchAt(opString, offset)!!
      val strings =
          lines.map {
            val range =
                if (match.range.start != match.range.endInclusive) match.range
                else match.range.start..it.lastIndex
            it.slice(range)
          }
      val op = if (match.groups[1]!!.value == "*") mult else add
      numStrs.add(strings to op)
      offset = match.range.last + 1
    }
    for ((strs, op) in numStrs) {
      val zipped = (0 until strs.minOf { it.length }).map { i -> strs.map { it[i] } }
      val numbers = mutableListOf<Long>()
      for (digits in zipped) {
        val n = digits.joinToString("").trim()
        if (n.isNotEmpty()) {
          numbers.add(digits.joinToString("").trim().toLong())
        }
      }
      count += op(numbers)
    }

    return count
  }

  performTest(
      fileName = "Day06",
      part1 = ::part1,
      part2 = ::part2,
      expectedPart1 = 4277556L,
      expectedPart2 = 3263827L,
  )
}
