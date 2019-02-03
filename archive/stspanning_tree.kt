import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList


fun main(args: Array<String>) {
    val sc = FastReaderS()
    val n = sc.nextInt()
    val m = sc.nextInt()

    val E = Array(m) {
        val u = sc.nextInt()
        val v = sc.nextInt()
        Pair(u - 1, v - 1)
    }

    val s = sc.nextInt() - 1
    val t = sc.nextInt() - 1
    var ds = sc.nextInt()
    var dt = sc.nextInt()

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

    val edges = ArrayList<Pair<Int, Int>>(m)

    val sEdges = ArrayList<Pair<Int, Int>>(n)

    val tEdges = ArrayList<Pair<Int, Int>>(n)

    for (e in E) {
        val (u, v) = e
        if (u == s || v == s) {
            sEdges.add(e)
        } else if (u == t || v == t) {
            tEdges.add(e)
        } else {
            if (find(u) != find(v)) {
                edges.add(e)
                union(u, v)
            }
        }
    }

    var st = false

    var sd = ds
    var a = 0

    var td = dt

    if (sEdges.size < tEdges.size) {
        while (sd > 0 && a < sEdges.size) {
            val e = sEdges[a]
            val (u, v) = e
            if (find(u) != find(v)) {
                if (u == t || v == t) {
                    st = true
                } else {
                    sd--
                    edges.add(e)
                    union(u, v)
                }
            }
            a++
        }

        a = 0
        while (td > 0 && a < tEdges.size) {
            val e = tEdges[a]
            val (u, v) = e
            if (find(u) != find(v)) {
                if (u == s || v == s) {
                    st = true
                } else {
                    td--
                    edges.add(e)
                    union(u, v)
                }
            }
            a++
        }
    } else {
        while (td > 0 && a < tEdges.size) {
            val e = tEdges[a]
            val (u, v) = e
            if (find(u) != find(v)) {
                if (u == s || v == s) {
                    st = true
                } else {
                    td--
                    edges.add(e)
                    union(u, v)
                }
            }
            a++
        }
        a = 0
        while (sd > 0 && a < sEdges.size) {
            val e = sEdges[a]
            val (u, v) = e
            if (find(u) != find(v)) {
                if (u == t || v == t) {
                    st = true
                } else {
                    sd--
                    edges.add(e)
                    union(u, v)
                }
            }
            a++
        }
    }


    if (sd > 0 && td > 0 && st) {
        if (find(s) != find(t)) {
            edges.add(Pair(s, t))
            union(s, t)
        }
    }

    var flag = true

    if (sEdges.size > ds) {
        for (i in ds until sEdges.size) {
            val e = sEdges[i]
            val (u, v) = e
            if (find(u) != find(v)) {
                flag = false
            }
        }
    }

    if (tEdges.size > dt) {
        for (i in ds until tEdges.size) {
            val e = tEdges[i]
            val (u, v) = e
            if (find(u) != find(v)) {
                flag = false
            }
        }
    }

    if (flag && find(s) == find(t)) {
        println("Yes")
        for ((u, v) in edges) {
            println("${u + 1} ${v + 1}")
        }
    } else {
        println("No")
    }


}