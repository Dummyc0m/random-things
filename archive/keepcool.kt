import java.util.*

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)

    val m = sc.nextInt()

    val n = m + 1

    val k = sc.nextInt()

    val x = sc.nextInt()

    val y = sc.nextInt()

    // starts at 0 0

    val umbrellas = Array(n + 1) { Triple(0, 0, 0) }

    for (i in 1..m) {
        umbrellas[i] = Triple(sc.nextInt(), sc.nextInt(), sc.nextInt())
    }
    umbrellas[n] = Triple(0, 0, 0) // car

    fun umbdist(x1: Int, y1: Int, r1: Int, x2: Int, y2: Int, r2: Int): Double {
        val dx = x1 - x2
        val dy = y1 - y2
        val dist = Math.sqrt((dx * dx + dy * dy).toDouble()) - r1 - r2
        return if (dist < 0) 0.0 else if (dist > k) Double.POSITIVE_INFINITY else dist
    }

    val mat = Array(n + 1) {Array(n + 1) { Double.POSITIVE_INFINITY }}

    for (i in 1..n) {
        mat[i][i] = 0.0
    }

    for (i in 1..n) {
        for (j in 1..n) {
            val umb1 = umbrellas[i]
            val umb2 = umbrellas[j]
            mat[i][j] = umbdist(umb1.first, umb1.second, umb1.third, umb2.first, umb2.second, umb2.third)
        }
    }

    for (k in 1..n) {
        for (i in 1..n) {
            for (j in 1..n) {
                mat[i][j] = mat[i][j].min(mat[i][k] + mat[k][j])
            }

        }
    }

    val direct = Math.sqrt((x * x + y * y).toDouble()).let {
        if (it <= k) it else Double.POSITIVE_INFINITY
    }
    var min = Double.POSITIVE_INFINITY
    for (i in 0..m) {
        val adist = if (i == 0) {
            direct
        } else {
            val a = umbrellas[i]
            val carA = mat[i][n]
            umbdist(a.first, a.second, a.third, x, y, 0) + carA
        }
        for (j in 0..m) {

            val bDist = if (j == 0) {
                if (i == 0) {
                    if (direct * 2 <= k) {
                        direct
                    } else {
                        Double.POSITIVE_INFINITY
                    }
                } else {
                    direct
                }
            } else {
                val b = umbrellas[j]
                val bCar = mat[n][j]
                umbdist(b.first, b.second, b.third, x, y, 0) + bCar
            }

            min = min.min(adist + bDist)
        }
    }

    if (min.isInfinite()) {
        println("-1")
    } else {
        println(min)
    }





//    umbrellas.sortWith {(x1, y1, r1), (x2, y2, z2) ->
//        if (x1 > x2) {
//            1
//        } else {
//
//        }
//    }
}

private fun Double.min(d: Double): Double {
    return if (this < d) this else d
}
