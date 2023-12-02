import kotlin.math.max

fun main() {

    val colorLimitMap = hashMapOf<String, Int>(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    fun validCubeCount(count: Long, color: String): Boolean {
        val colorLimit = colorLimitMap[color]
        if (colorLimit != null) {
            return colorLimit >= count
        }
        return true
    }

    fun part1(input: List<String>): Int {
        var totaledIds = 0
        input.forEach { line ->
            val lineParts = line.split(":")
            val header = lineParts.first()
            val id = header.split(" ").last().toInt()
            val body = lineParts.last()
            val handfuls = body.split(";")
            var validLine = true
            handfuls.forEach { handfulString ->
                val cubes = handfulString.split(",")
                var validHandful = true
                cubes.forEach { it ->
                    val cubeCountString = it.split(" ").filter {it.isNotEmpty()}
                    val count = cubeCountString.first().toLong()
                    val color = cubeCountString.last()
                    if (!validCubeCount(count, color)) {
                        validHandful = false
                    }
                }
                if (!validHandful) {
                    validLine = false
                }
            }
            if (validLine) {
                totaledIds += id
            }
        }
        return totaledIds
    }

    fun part2(input: List<String>): Int {
        var totalPower = 0
        input.forEach { line ->
            val lineParts = line.split(":")
            val body = lineParts.last()
            val handfuls = body.split(";")
            var maxColors = mutableMapOf<String, Int>()

            handfuls.forEach { handfulString ->
                val cubes = handfulString.split(",")
                cubes.forEach { it ->
                    val cubeCountString = it.split(" ").filter {it.isNotEmpty()}
                    val count = cubeCountString.first().toInt()
                    val color = cubeCountString.last()

                    val previousCount = maxColors[color] ?: 0
                    maxColors[color] = max(previousCount, count)
                }
            }
            val power = maxColors.values.reduce(Int::times)
            totalPower += power
        }
        return totalPower
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)

    val testInput2 = readInput("Day02_pt2_test")
    check(part2(testInput2) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
