fun main() {
    fun String.toIntRange(): List<IntRange> =
        this.split(",")
            .map {
                it.substringBefore("-").toInt() .. it.substringAfter("-").toInt()
            }

    infix fun IntRange.fullyOverlaps(other: IntRange): Boolean = first <= other.first && last >= other.last
    infix fun IntRange.overlaps(other: IntRange): Boolean = first <= other.last && other.first <= last

    fun part1(input: List<String>): Int {
        return input.sumOf {
            it.toIntRange()
                .let { (firstElf, secondElf) ->
                    if (firstElf fullyOverlaps secondElf || secondElf fullyOverlaps firstElf) 1 else 0 as Int
                }
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf {
            it.toIntRange()
                .let { (firstElf, secondElf) -> if (firstElf overlaps secondElf) 1 else 0 as Int }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
