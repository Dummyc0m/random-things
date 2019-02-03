import java.util.*

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val n = sc.nextInt()

    if (n % 2 == 1) {
        println("-1")
        return
    }

    val adjlist = Array(n+1) {LinkedList<Int>()}

    for (i in 1..(n - 1)) {
        // edges
        val p = sc.nextInt()
        val q = sc.nextInt()
        adjlist[p].add(q)
        adjlist[q].add(p)

    }

    val dfsStack = Stack<Int>()
    val mark = Array<Boolean>(n+1) {false}

    while(dfsStack.isNotEmpty()) {
    }

    // cuts, iseven
    val nop = Pair(-1, true)

    val end = Pair(0, false)

    fun rec(v: Int): Pair<Int, Boolean> {
        if (!mark[v]) {
            mark[v] = true
            val thing = adjlist[v].map { rec(it) }
            if (thing.isEmpty()) {
                return end
            }
            var numOfOdd = 1
            var cuts = 0

            for (a in thing) {
                if (a == nop) {
                    continue
                }
                cuts += a.first
                if (a.second) cuts++
                else {
                    numOfOdd++
                }
            }
            return Pair(cuts, numOfOdd % 2 == 0)
        } else {
            return nop
        }
    }

    val res = rec(1)

    if (res.second) {
        println(res.first)
    } else {
        println("-1")
    }




}
