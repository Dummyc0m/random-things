import java.util.*
import java.lang.Math.min

class TarjanSCC {

    internal var n: Int = 0
    internal var count: Int = 0
    internal var comp: Int = 0
    internal var num: IntArray
    internal var low: IntArray
    internal var answer: IntArray
    internal var onStack: BooleanArray
    internal var stack: Stack<Int>
    internal var graph: Array<IntArray>

    init {
        num = IntArray(n)
        low = IntArray(n)
        answer = IntArray(n)
        onStack = BooleanArray(n)
        stack = Stack()
        graph = Array(0) {IntArray(1) {0}}
    }


    fun strong(g: Array<IntArray>): IntArray {
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


    internal fun DFS(v: Int) {
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