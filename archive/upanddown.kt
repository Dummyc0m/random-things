import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
fun main(args: Array<String>) {
    val sc = FastReader()

    val n = sc.nextInt()
    val k = sc.nextInt()
    val adjlist = Array(n+1) {LinkedList<Int>()}

    for (i in 2..n) {
        // edges
        val p = sc.nextInt()
        adjlist[p].add(i)
    }

    // expiring

    fun countl(start: Int, depth: Int): Pair<Int, Int> {
        return if (adjlist[start].isEmpty()) Pair(1, depth)
        else adjlist[start].map { countl(it, depth + 1) }.fold(0) { a, b ->

        }
    }

    var res = countl(1, 0)
}
