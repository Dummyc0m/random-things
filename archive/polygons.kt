import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

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

data class Point(val x: Int, val y: Int): Comparable<Point> {
    override fun compareTo(other: Point): Int {
       return Integer.compare(this.x, other.y)
    }
}

fun main(args: Array<String>) {
    val pq = PriorityQueue<Triple<Point, Boolean, Boolean>>(Comparator { a, b -> a.first.compareTo(b.first) })

    val sc = FastReaderS()

    val n = sc.nextInt()

    var prevX: Int? = null
    var prevY: Int? = null
    for (i in 1..n) {
        // is start
        val x = sc.nextInt()
        val y = sc.nextInt()
        pq.add(Triple(Point(prevX!!, prevY!!), true, true))
        pq.add(Triple(Point(x, y), false, true))
        prevX = x
        prevY = y
    }

    val m = sc.nextInt()

    prevX = null
    prevY = null
    for (i in 1..m) {

        // is start
        val x = sc.nextInt()
        val y = sc.nextInt()
        if (prevX != null && prevY != null) {
            pq.add(Triple(Point(prevX, prevY), true, false))
            pq.add(Triple(Point(x, y), false, false))
        }
        prevX = x
        prevY = y
    }

    val tr = TreeSet<Pair<Point, Boolean>>()

    tr



}