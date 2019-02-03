import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashSet

class FastReaderI {
    var br: BufferedReader = BufferedReader(InputStreamReader(System.`in`))
    var st: StringTokenizer? = null

    operator fun next(): String {
        while (st == null || !st!!.hasMoreElements()) {
            try {
                st = StringTokenizer(br.readLine())
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return st!!.nextToken()
    }

    fun nextInt(): Int {
        return Integer.parseInt(next())
    }

    fun nextLong(): Long {
        return java.lang.Long.parseLong(next())
    }

    fun nextDouble(): Double {
        return java.lang.Double.parseDouble(next())
    }

    fun nextLine(): String {
        var str = ""
        try {
            str = br.readLine()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return str
    }
}

fun main(args: Array<String>) {
    val sc = FastReaderI()

    val n = sc.nextInt()

    val costs = Array<Int>(n) {Int.MAX_VALUE}

    for (i in 0 until n) {
        costs[i] = sc.nextInt()
    }

    val graph = Array(n) {0}
    val prev = Array(n) {-1}

    for (i in 0 until n) {
        val next = sc.nextInt() - 1
        graph[i] = next
        prev[next] = i
    }


//    val disj = Array(n) {it}
//    val rank = Array(n) {0}
//
//    tailrec fun find(start: Int): Int {
//        return if (start == disj[start]) start
//        else find(disj[start])
//    }
//
//    fun union(x: Int, y: Int) {
//        val rootX = find(x)
//        val rootY = find(y)
//        if (rootX != rootY) {
//            when {
//                rank[rootX] > rank[rootY] -> disj[rootX] = rootY
//                rank[rootY] > rank[rootX] -> disj[rootY] = rootX
//                else -> {
//                    rank[rootX]++
//                    disj[rootY] = rootX
//                }
//            }
//        }
//    }
//
//    val unvisited = HashSet<Int>(n * 2)
//    for (i in 0 until n)
//        unvisited.add(i)

    tailrec fun findRoot(start: Int, whereWeStarted: Int?): Int {
        if (whereWeStarted == start) return start
        val started = whereWeStarted ?: start
        return if (prev[start] == -1) start
        else findRoot(prev[start], started)
    }

    tailrec fun minCostFrom(start: Int, curr: Int, cost: Int): Int {
        return if (start == curr) Math.min(cost, costs[curr])
        else minCostFrom(start, graph[curr], Math.min(cost, costs[curr]))
    }


    var minCost = 0

    val visitedMask = Array(n) {Int.MAX_VALUE}
    var currentRound = 0
//    while (unvisited.isNotEmpty()) {
    for (i in 0 until n) {
//        var working = findRoot(unvisited.first(), null)
        if (visitedMask[i] < Int.MAX_VALUE)
            continue
        var working = i

        while (true) {
            val visit = visitedMask[working]
            if (visit < currentRound) {
                // cycle
                break
            } else if (visit == currentRound) {
                minCost += minCostFrom(working, graph[working], Int.MAX_VALUE)
                break
            }
//            unvisited.remove(working)
            visitedMask[working] = currentRound
            working = graph[working]
        }

        currentRound++
    }

    println(minCost)
}
