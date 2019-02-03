import java.util.*

class Graph {
    private val adjlist: MutableMap<String, MutableSet<String>> = hashMapOf()

    fun addEdge(a: String, b: String): Graph {
        val existing = adjlist[a]
        if (existing == null) {
            val new = hashSetOf(b)
            adjlist[a] = new
        } else {
            existing.add(b)
        }
        return this
    }

    fun isConnected(a: String, b: String): Boolean {
        fun isConnectedRec(a: String, b: String, visited: MutableSet<String>): Boolean {
            if (a == b) return true
            if (visited.contains(a)) return false
            visited.add(a)
            return adjlist[a]?.map { isConnectedRec(it, b, visited) }?.reduce { i, j -> i || j } ?: false
        }

        return isConnectedRec(a, b, hashSetOf())
    }
}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val n = sc.nextInt()
    val m = sc.nextInt()

    val g = Graph()

    sc.nextLine()
    for (i in 1..n) {
        val line = sc.nextLine()
        val stuff = line.split(" ")
        g.addEdge(stuff.first(), stuff.last())
    }

    for (i in 1..m) {
        val line = sc.nextLine()
        val stuff = line.split(" ")
        val a = stuff.first()
        val b = stuff.last()
        when {
            g.isConnected(a, b) -> println("Fact")
            g.isConnected(b, a) -> println("Alternative Fact")
            else -> println("Pants on Fire")
        }
    }
}
