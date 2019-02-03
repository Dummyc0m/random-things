import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap


fun main(args: Array<String>) {
    val sc = FastReaderS()
    val n = sc.nextInt()

    val disj = Array(n) {it}
    val rank = Array(n) {0}

    fun find(start: Int): Int {
        return if (start == disj[start]) start
        else find(disj[start]).also { disj[start] = it }
    }


    fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX != rootY) {
            when {
                rank[rootX] > rank[rootY] -> disj[rootX] = rootY
                rank[rootY] > rank[rootX] -> disj[rootY] = rootX
                else -> {
                    rank[rootX]++
                    disj[rootY] = rootX
                }
            }
        }
    }
//
//    val unvisited = HashSet<Int>(n * 2)
//    for (i in 0 until n)
//        unvisited.add(i)

    val arr = Array(n) {
        sc.nextInt()
    }

    for (i in 0 until n) {
        val str = sc.nextLine()

        for (j in 0 until n) {
            val c = str[j]
            if (c == '1') {
                union(i, j)
            }
        }
    }

    val rootSets = HashMap<Int, PriorityQueue<Int>>()

    for (i in 0 until n) {
        val root = find(i)
        val set = rootSets[root] ?: PriorityQueue<Int>().also {rootSets[root] = it}

        set.add(arr[i])
    }

    for (i in 0 until n) {
        val root = find(i)

        val set = rootSets[root]!!

        val num = set.poll()
        print("$num ")
    }
    print("\n")

}
