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

fun xor1ton(n: Long): Long {
    return when (n % 4L) {
        0L -> n
        1L -> 1L
        2L -> n + 1L
        3L -> 0
        else -> 0
    }
}

fun main(args: Array<String>) {
    val sc = FastReaderS()

    val n = sc.nextLong()
    var tally = 0L
    for (i in 1..n) {
        val x = sc.nextLong()
        val m = sc.nextLong()
        val fnd = xor1ton(x + m - 1L)
        val fst = if (x > 1) xor1ton(x - 1L) else 0L
        tally = tally xor (fnd xor fst)
    }

    if (tally == 0L) {
        println("bolik")
    } else {
        println("tolik")
    }
}