private data class PointAndDistance(
    val x: Coordinate3D,
    val y: Coordinate3D,
    val distance: Double,
) {}

fun main() {
  fun part1(input: List<String>): Long {
    val coordinates = input.map(Coordinate3D::fromString)
    val iterations = if (input.size == 20) 10 else 1000
    val pairs = coordinates.allPairs()
    val distances =
        pairs
            .map { PointAndDistance(it.first, it.second, it.first.euclideanDistance(it.second)) }
            .sortedBy { it.distance }
            .toMutableList()
    val circuits = mutableListOf<MutableSet<Coordinate3D>>()
    val directlyConnected = mutableSetOf<Pair<Coordinate3D, Coordinate3D>>()
    for (ignored in 1..iterations) {
      if (distances.isEmpty()) break
      val (minFirst, minSecond) = distances.removeFirst()
      val circuitFirst = circuits.firstOrNull { minFirst in it }
      val circuitSecond = circuits.firstOrNull { minSecond in it }
      if (circuitFirst == null && circuitSecond == null) {
        circuits.add(mutableSetOf(minFirst, minSecond))
      } else if (circuitFirst != circuitSecond) {
        if (circuitFirst != null && circuitSecond == null) {
          circuitFirst.add(minSecond)
        } else if (circuitFirst == null && circuitSecond != null) {
          circuitSecond.add(minFirst)
        } else {
          circuitFirst!!.addAll(circuitSecond!!)
          circuits.remove(circuitSecond)
        }
      }
      directlyConnected.add(minFirst to minSecond)
    }
    val left = coordinates.filterNot { coord -> circuits.any { coord in it } }
    left.forEach { circuits.add(mutableSetOf(it)) }
    return circuits
        .map { it.size.toLong() }
        .sortedDescending()
        .take(3)
        .reduce(Math::multiplyExact)
        .toLong()
  }

  fun part2(input: List<String>): Long {
    val coordinates = input.map(Coordinate3D::fromString)
    val iterations = if (input.size == 20) 10 else 1000
    val pairs = coordinates.allPairs()
    val distances =
        pairs
            .map { PointAndDistance(it.first, it.second, it.first.euclideanDistance(it.second)) }
            .sortedBy { it.distance }
            .toMutableList()
    val circuits = mutableListOf<MutableSet<Coordinate3D>>()
    val directlyConnected = mutableSetOf<Pair<Coordinate3D, Coordinate3D>>()
    while (true) {
      if (distances.isEmpty()) break
      val (minFirst, minSecond) = distances.removeFirst()
      val circuitFirst = circuits.firstOrNull { minFirst in it }
      val circuitSecond = circuits.firstOrNull { minSecond in it }
      if (circuitFirst == null && circuitSecond == null) {
        circuits.add(mutableSetOf(minFirst, minSecond))
      } else if (circuitFirst != circuitSecond) {
        if (circuitFirst != null && circuitSecond == null) {
          circuitFirst.add(minSecond)
        } else if (circuitFirst == null && circuitSecond != null) {
          circuitSecond.add(minFirst)
        } else {
          circuitFirst!!.addAll(circuitSecond!!)
          circuits.remove(circuitSecond)
        }
      }
      directlyConnected.add(minFirst to minSecond)
      if (circuits.size == 1 && circuits.first().containsAll(coordinates)) {
        return minFirst.x.toLong() * minSecond.x.toLong()
      }
    }
    val left = coordinates.filterNot { coord -> circuits.any { coord in it } }
    left.forEach { circuits.add(mutableSetOf(it)) }
    return circuits
        .map { it.size.toLong() }
        .sortedDescending()
        .take(3)
        .reduce(Math::multiplyExact)
        .toLong()
  }
  performTest(
      fileName = "Day08",
      part1 = ::part1,
      part2 = ::part2,
      expectedPart1 = 40L,
      expectedPart2 = 25272L,
  )
}
