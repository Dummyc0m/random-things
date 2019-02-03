import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)

    val t = sc.nextInt()
    sc.nextLine()

    for (tt in 1..t) {
        val n = sc.nextInt()
        sc.nextLine()

        val t1map = HashMap<String, Int>(n)

        for (i in 1..n) {
            val team = sc.nextLine()
            t1map[team] = i
        }

        var currentS = 0

        var target = 1

        val sizes = ArrayList<Int>()

        for (j in 1..n) {
            val team = sc.nextLine()
            val t1loc = t1map[team]!!
            currentS++
            target = max(t1loc, target)
            if (j == target) {
                sizes.add(currentS)
                currentS = 0
            }
        }

        val output = sizes.fold(StringBuilder()) { acc, v -> acc.append(v).append(" ") }
        output.deleteCharAt(output.lastIndex)
        println(output.toString())
    }
}