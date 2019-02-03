import java.util.*
import java.io.IOException
import java.util.StringTokenizer
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.min
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

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
    val m = sc.nextInt()

    val cost = Array(n){0}
    for (i in 0 until n) {
        cost[i] = sc.nextInt()
    }

    val graph = Array(n) {ArrayList<Int>()}

    for (i in 0 until m) {
        val p = sc.nextInt() - 1
        val q = sc.nextInt() - 1

        graph[p].add(q)
        graph[q].add(p)
    }

    val res = TarjanSCC().strong(graph)

    // component to max cost
    val comps = HashMap<Int, Int>()

    for (i in res.indices) {
        // comp
        val v = res[i]
        val c = cost[i]
        val existing = comps[v] ?: Int.MAX_VALUE
        comps[v] = Math.min(existing, c)
    }

    val total = comps.entries.fold(0.toBigInteger()) {
        acc, entry -> acc + entry.value.toBigInteger()
    }

    println(total)
}
