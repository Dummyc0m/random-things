import java.lang.Integer.max
import java.lang.Integer.min
import java.util.*

fun main (args: Array<String>) {
    val sc = Scanner(System.`in`)

    val T = sc.nextInt()

    for (i in 1..T) {
        val N = sc.nextInt()
        val M = sc.nextInt()

        val C = sc.nextInt()
        val K = sc.nextInt()

        val h = Array(N + 1) { Array(M + 1) {0} }
//        val h = HashMap<Pair<Int, Int>, Int>()


        var grid = 0
        for (j in 1..C) {
            val x = sc.nextInt()
            val y = sc.nextInt()

            val r = sc.nextInt()
            val rr = r * r

            for (xx in max(1, x - r)..min(N, x + r)) {
                for (yy in max(1, y - r)..min(M, y + r)) {
                    val a = xx - x
                    val b = yy - y
                    if (a * a + b * b <= rr) {
                            val got = ++h[xx][yy]
                            if (K == got) {
                                grid++
                            }
                    }
                }
            }


        }
        println(grid)

    }
}

