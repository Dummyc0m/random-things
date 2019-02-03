import java.math.BigInteger
import java.util.*

fun Int.big() = this.toBigInteger()

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val n = sc.nextBigInteger()
    val k = sc.nextBigInteger()
    val p = sc.nextBigInteger()

    val special = (p == 2.big())

    val twoN = fastexp(2.toBigInteger(), n, p - 1.toBigInteger())

    val exponent = clamp(twoN - n, p - 1.toBigInteger())

    val withoutOffset = if (special) 0.big() else fastexp(2.toBigInteger(), exponent, p)

        if (k == 0.toBigInteger()) {
            println(clamp(withoutOffset - 1.toBigInteger(), p).toString())
        } else {
            println(clamp(withoutOffset, p).toString())
        }

}

fun clamp(a: BigInteger, n: BigInteger): BigInteger {
    val aModN = a % n
    return if (aModN < 0.toBigInteger()) aModN + n else aModN
}

fun fastexp(a: BigInteger, e: BigInteger, n: BigInteger): BigInteger {
    return with(generateSequence(a) { (it * it) % n }) {
        val str = e.toString(2)
        val iter = iterator()
        str.foldRight(BigInteger.ONE) { c, acc ->
            val exp = iter.next()
            (acc * (if (c == '1') exp else BigInteger.ONE)) % n
        }
    }
}
