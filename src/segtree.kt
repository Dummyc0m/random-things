import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

class SegTree(s: Int) {

    val size: Int
    init {
        var power = 1
        while (power < s)
            power *= 2
        size = power
    }

    val arr = Array(2 * size) {0}

    inline fun parent(v: Int): Int {
        return v / 2
    }

    inline fun lc(v: Int): Int {
        return 2 * v
    }

    inline fun rc(v: Int): Int {
        return 2 * v + 1
    }

    inline fun assign(v: Int, x: Int) {
        // We want to assign the value of A[v] to x.
        // This is the same as doing the assignment A[v] += x-A[v]
        // To achieve this we have to add this quantity to
        // every node on the path from v to the root.
        var x = x - arr[size + v]
        var j = size + v
        while (j > 0) {
            arr[j] += x
            j = parent(j)
        }
    }

    fun f(v: Int, l: Int, r: Int, ql: Int, qr: Int): Int {
        // [l,r] is the range represented by the leaves at
        // the bottom of the subtree rooted at v.
        // [ql,qr] is the query range.  It's always within [l,r]

        val m = (l + r) / 2  // left range is [l,m].  right range is [m+1,r]

//        println("v=$v, l=$l, r=$r, ql=$ql, qr=$qr")

        if (l == ql && r == qr) return arr[v]
        var total = 0
        if (ql <= m)
            total += f(lc(v), l, m, ql, Math.min(m, qr))
        if (qr > m)
            total += f(rc(v), m + 1, r, Math.max(ql, m + 1), qr)
        return total
    }

    inline fun sum(i: Int, j: Int): Int {
        return f(1, 0, size - 1, i, j)
    }
}

class FastReaderS {
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
//    System.setIn(FileInputStream("/Users/dummy/Downloads/Untitled.txt"))
    val sc = FastReaderS()
    val n = sc.nextInt()
    val m = sc.nextInt()

    val segtree = SegTree(m + n)

    for (i in 0 until n) {
        segtree.assign(m + i, 1)
    }

    var nextFree = m - 1

    val arrMapping = Array(n) {it + m}

    val output = StringBuilder(2 * m)
    for (c in 1 .. m) {
        val toMove = sc.nextInt()

        val curr = arrMapping[toMove]

        output.append(segtree.sum(nextFree + 1, curr - 1))
        output.append(' ')

        segtree.assign(curr, 0)
        segtree.assign(nextFree, 1)

        arrMapping[toMove] = nextFree
        nextFree--
    }

    print(output)
}