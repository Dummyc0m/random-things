import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigInteger
import java.util.*

fun main(args: Array<String>) {
//    val br = BufferedReader(InputStreamReader(System.`in`))

    val sc = Scanner(System.`in`)

    val n = sc.nextInt()
    val l = sc.nextInt()

    val prices = Array(n + 1) {
        0
    }

    for (i in 1..n) {
        prices[i] = sc.nextInt()
    }

    fun getMin (l: Int, n: Int): Long {
        if (n <= 0) return Long.MAX_VALUE
        var bots = l ushr (n - 1)
        if (bots shl (n - 1) < l) bots++
        var min = (n - 1).let {
            bots.toLong() * prices[n].toLong()
        }

        for (i in 0..(bots - 1)) {
            val rest = l - i shl (n - 1)
            min = min.min(i * prices[n].toLong() + getMin(rest, n - 1))
        }

        return min
    }
    println(getMin(l, n))
}

private fun Long.min(l: Long): Long {
    return if (this < l) this else l
}
