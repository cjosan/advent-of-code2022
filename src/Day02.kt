fun main() {
    fun part1(input: List<String>): Int {
        // A -> Rock; B -> Paper; C -> Scissors
        // X -> Rock; Y -> Paper; Z -> Scissors
        val shapeScores = mapOf("X" to 1, "Y" to 2, "Z" to 3)

        return input
            .stream()
            .mapToInt { round ->
                val shapes = round.split(" ")
                val roundResult = shapes[1].single() - shapes[0].single()

                var score = shapeScores.getValue(shapes[1])
                // 23 -> draw; 21, 24 -> win; 22, 25 -> lose
                if (roundResult == 23) {
                    score += 3
                } else if (roundResult == 21 || roundResult == 24) {
                    score += 6
                }

                score
            }
            .sum()
    }

    fun part2(input: List<String>): Int {
        // X -> lose; Y -> draw; Z -> win
        val roundResultScore = mapOf('X' to 0, 'Y' to 3, 'Z' to 6)
        // A -> Rock; B -> Paper; C -> Scissors
        val shapeScores = mapOf('A' to 1, 'B' to 2, 'C' to 3)

        return input
            .stream()
            .mapToInt { round ->
                val shapes = round.split(" ").map { it.single() }

                var score = roundResultScore.getValue(shapes[1])
                when (score) {
                    3 -> score += shapeScores.getValue(shapes[0])
                    6 -> score += shapeScores.getOrDefault((shapes[0] + 1), 1)
                    0 -> score += shapeScores.getOrDefault((shapes[0] - 1), 3)
                }

                score
            }
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
