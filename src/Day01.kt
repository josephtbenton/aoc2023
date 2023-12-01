fun main() {

    fun firstDigit(input: String): Int {
        val head = input.takeWhile { !it.isDigit() }
        val digitChar = input[head.length]
        return Character.getNumericValue(digitChar)
    }

    fun lastDigit(input: String): Int {
        val tail = input.takeLastWhile { !it.isDigit() }
        val digitChar = input[input.length - 1 - tail.length]
        return Character.getNumericValue(digitChar)
    }

    val wordToIntMap: Map<String, Int> = hashMapOf(
            "zero" to 0,
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )

    fun firstAndLastDigitSearch(input: String): Pair<Int, Int> {
        var firstDigit: Int? = null
        var lastDigit = 0

        for (i in input.indices) {
            var foundDigit: Int? = null
            if (input[i].isDigit()) {
                foundDigit = Character.getNumericValue(input[i])
            } else {
                for (digitString in wordToIntMap.keys) {
                    val found = input.regionMatches(i, digitString, 0, digitString.length)
                    if (found) {
                        foundDigit = wordToIntMap[digitString]
                    }
                }
            }
            if (foundDigit != null) {
                if (firstDigit == null) {
                    firstDigit = foundDigit
                }
                lastDigit = foundDigit
            }
        }
        return Pair(firstDigit!!, lastDigit)
    }

    fun part1(input: List<String>): Int {
        var result = 0
        input.forEach {
            val tens = firstDigit(it)
            val ones = lastDigit(it)
            result += tens * 10 + ones
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        input.forEach {
            val (tens, ones) = firstAndLastDigitSearch(it)
            result += tens * 10 + ones
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val testInput2 = readInput("Day01_pt2_test")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
