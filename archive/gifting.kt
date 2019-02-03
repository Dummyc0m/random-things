import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
//    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = sc.nextInt()
    val m = sc.nextInt()

    val source = 2 * n + 1
    val sink = 2 * n + 2

    val graph = Array(2 * n + 3) { HashMap<Int, Int>() }

//    val AS = Array(n + 1) {0}
//    val BS = Array(n + 1) {0}

    for (i in 1..n) {
//        AS[i] = sc.nextInt()
//        graph[source][i] = sc.nextInt()
        graph[source][i] = 1000000
    }

    for (i in 1..n) {
//        BS[i] = sc.nextInt()
//        graph[i + n][sink] = sc.nextInt()

//        graph[i][i + n] = 1000000
        graph[i + n][sink] = 1
    }

    for (i in 1..m) {
        val p = sc.nextInt()
        val q = sc.nextInt()

        graph[p][q + n] = 1
        graph[q][p + n] = 1
    }

    fun solve(source: Int, sink: Int, graph: Array<HashMap<Int, Int>>): Array<Array<Int>> {
        val flowing = Array(2 * n + 3) { Array(2 * n + 3) {0}}

        while(true) {
            // capacity path to node
            var cap = Array(2 * n + 3) {0}
            cap[source] = Int.MAX_VALUE
            var queue = LinkedList<Int>()

            val parent = Array(2 * n + 3) {-1}
            parent[source] = source

            queue.offer(source)
            a@while (queue.isNotEmpty()) {
                val curr = queue.poll()

                b@for (ve in graph[curr].entries) {
                    val availableFlow = ve.value - flowing[curr][ve.key]
                    if (parent[ve.key] == -1 && availableFlow > 0) {
                        parent[ve.key] = curr
                        cap[ve.key] = Math.min(cap[curr], availableFlow)
                        if (ve.key != sink) {
                            queue.offer(ve.key)
                        } else {
                            // we are here
                            var c = sink

                            while (parent[c] != c) {
                                val p = parent[c]
                                val forward = flowing[p][c]
                                flowing[p][c] = forward + cap[sink]
                                val backward = flowing[c][p]
                                flowing[c][p] = backward - cap[sink]
                                if (graph[c][p] == null) {
                                    graph[c][p] = 0
                                }
                                c = p
                            }

                            break@a
                        }
                    }
                }

            }
            if (parent[sink] == -1) {
                // no augmenting path
                return flowing

            }
        }
    }


    val solution = solve(source, sink, graph)

    for (i in 1..n) {
        if (graph[source][i] != solution[source][i] || graph[i + n][sink] != solution[i + n][sink]) {
            println("NO")
            return
        }
    }

    println("YES")

    for (i in 1..n) {
        for (j in 1..n) {
            print("${solution[i][j + n]} ")
        }
        println()
    }
}
