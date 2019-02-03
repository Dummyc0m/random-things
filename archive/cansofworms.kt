import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Integer.min
import java.util.*

class TarjanSCC {
    internal var n: Int = 0
    internal var count: Int = 0
    internal var comp: Int = 0
    internal var num: IntArray
    internal var low: IntArray
    internal var answer: IntArray
    internal var onStack: BooleanArray
    internal var stack: Stack<Int>
    internal var graph: Array<ArrayList<Int>>

    init {
        num = IntArray(n)
        low = IntArray(n)
        answer = IntArray(n)
        onStack = BooleanArray(n)
        stack = Stack()
        graph = Array(1) {ArrayList<Int>() }
    }


    fun strong(g: Array<ArrayList<Int>>): IntArray {
        graph = g
        n = graph.size
        num = IntArray(n)
        low = IntArray(n)
        answer = IntArray(n)
        onStack = BooleanArray(n)
        stack = Stack()
        count = 0
        comp = 0
        for (x in 0 until n) DFS(x)
        return answer
    }


    private fun DFS(v: Int) {
        if (num[v] != 0) return
        low[v] = ++count
        num[v] = low[v]
        stack.push(v)
        onStack[v] = true
        for (w in graph[v]) DFS(w)
        for (w in graph[v]) if (onStack[w]) low[v] = min(low[v], low[w])
        if (num[v] == low[v]) {
            while (true) {
                val x = stack.pop()
                onStack[x] = false
                answer[x] = comp
                if (x == v) break
            }
            comp++
        }
    }
}

class FastReader {
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
    val sc = FastReader()

    val n = sc.nextInt()

    fun inrange(x: Int, loc: Int, rad: Int): Boolean {
        return x in (loc - rad)..(loc + rad)
    }

    val empty = Pair(0, 0)
    val locs = Array(n) {empty}

    for (i in 0 until n) {
        val loc = sc.nextInt()
        val rad = sc.nextInt()
        locs[i] = Pair(loc, rad)
    }

    locs.sortWith(Comparator { o1, o2 ->
        o1.first - o2.first
    })

    val graph = Array(n) {ArrayList<Int>()}

    fun recurAdd(pair: Pair<Int, Int>, acc: Stack<Pair<Int, Pair<Int, Int>>>, pairInd: Int) {
        if (acc.empty()) return
        val head = acc.peek()
        if (inrange(head.second.first, pair.first, pair.second)) {
            // add edge from pair to head
            graph[pairInd].add(head.first)
            acc.pop()
            recurAdd(pair, acc, pairInd)
        } else {
            return
        }
    }

    locs.foldRightIndexed(Stack<Pair<Int, Pair<Int, Int>>>()) {ind, pair, acc ->
        recurAdd(pair, acc, ind)
        acc.push(Pair(ind, pair))
        acc
    }

    locs.foldIndexed(Stack<Pair<Int, Pair<Int, Int>>>()) {ind, acc, pair ->
        recurAdd(pair, acc, ind)
        acc.push(Pair(ind, pair))
        acc
    }

    val tarj = TarjanSCC()

    val comp = tarj.strong(graph)

    


}
