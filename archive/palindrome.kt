import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
//    val sc = Scanner(System.`in`)
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()

    val T = HashMap<Int, Int>()

    val arr = Array(n) {
        val str = br.readLine()

        var mask = 0
        str.forEach {
            val b = it - 'a'
            val m = 1 shl b
            mask = mask xor m
        }
        T[mask] = (T[mask] ?: 0) + 1

        mask
    }

//    val masks = Array(26) {
//        1 shl it
//    }

    var c = 0L

    for (i in 0 until n) {
        val a = arr[i]

        val occ = (T[a] ?: 0) - 1
        c += occ.toLong()

        for (i in 0 until 26) {
            val cc = (1 shl i) xor a
            if (cc != a) {
                val occ = (T[cc] ?: 0)
                c += occ.toLong()
            }
        }
    }

    println(c shr 1)
}
