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

fun main(args: Array<String>) {
    val sc = FastReaderS()

    val n = sc.nextInt()

    val arr = Array(n) {
        val x = sc.nextInt()
        val y = sc.nextInt()
        val deg = Math.toDegrees(Math.atan2(x.toDouble(), y.toDouble()))
        if (deg < 0) {
            deg + 360.0
        } else {
            deg
        }
    }

    val sorted = arr.sortedArray()

    var max = 360.0 - (sorted.last() - sorted.first())

    for (i in 0 until (n - 1)) {
        val diff = sorted[i + 1] - sorted[i]
        if (diff > max) {
            max = diff
        }
    }

    println(360.0 - max)
}