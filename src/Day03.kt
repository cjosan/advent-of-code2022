fun main() {
    fun Char.toPriority() = (if (isLowerCase()) this - 'a' else this - 'A' + 26) + 1
    fun String.splitToSets() = Pair(
        this.substring(0, this.length / 2).toSet(),
        this.substring(this.length / 2).toSet()
    )

    fun part1(input: List<String>): Int {
        return input.sumOf { rucksack ->
            rucksack
                .splitToSets()
                .let { (first, second) -> first intersect second }
                .single()
                .toPriority()
        }
    }

    fun part2(input: List<String>): Int {
        return input
            .chunked(3) {
                it.map { elf ->
                    elf.toSet()
                }.let { (first, second, third) ->
                    first intersect second intersect third
                }.single().toPriority()
            }
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
