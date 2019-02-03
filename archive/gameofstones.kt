import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

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

class SolPair(val v: Int, val hashSet: HashSet<Int>) {
    val filtered: Sequence<Int> = hashSet.asSequence().filter {it <= v}.sorted()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SolPair

        if (v != other.v) return false
        if (filtered != filtered) return false

        return true
    }

    override fun hashCode(): Int {
        var result = v
        result = 31 * result + filtered.hashCode()
        return result
    }
}

val memo = HashMap<Pair<Int, Long>, Int>()

fun calcNim(v: Int): Int {
    fun calcNimRec(v: Int, chosen: Long): Int {
        val key= Pair(v, chosen)
        val found = memo[key]
        if (found != null) return found
        if (v == 0) return 0

        val nimVals = BooleanArray(v) { true }
        for (i in 1..v) {
            val mask = chosen shr i
            if ((mask and 1L) == 0L) {
                val maskp = chosen or (1L shl i)
                val n = calcNimRec(v - i, maskp)

                nimVals[n] = false
            }
        }

        val idx =  nimVals.indexOfFirst { it }
        val ret = if (idx == -1) {
            v
        } else idx

        memo[key] = ret
        return ret
    }

    return calcNimRec(v, 0L)
}

fun main(arr: Array<String>) {
    val sc = FastReaderS()

//    val nimNums = IntArray(61) { calcNim(it) }
//    println(Arrays.toString(nimNums))
    val nimNums = arrayOf(0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10)

    val n = sc.nextInt()

    var nimXor = 0
    // stuff
    for (i in 1..n) {
        val s = sc.nextInt()
        nimXor = nimXor xor (nimNums[s])
    }

    if (nimXor == 0) {
        println("YES")
    } else {
        println("NO")
    }
}