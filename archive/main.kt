import java.math.BigInteger
import java.util.*

/**
 * secret: -185344438947435257
 * why study when you can watch game of thrones
 */
fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    println("gimme a P")
    val p = sc.next().toBigInteger()
    println("gimme a Q")
    val q = sc.next().toBigInteger()
    println("gimme a E")
    val e = sc.next().toBigInteger()

    println("How many messages?")
    val cipher = List(sc.nextInt()) { println("gimme a message"); sc.next().toBigInteger() }

    println("here is ya message")
    val n = p * q
    val phi = (p - BigInteger.ONE) * (q - BigInteger.ONE)
    val (_, eInverse, _) = extendedEuclidean(e, phi)
    println("ya secret: $eInverse")

    fun decode(m: BigInteger, acc: StringBuilder): StringBuilder = when (m) {
        BigInteger.ZERO -> acc
        else -> decode(m / 27.toBigInteger(), acc.append((m % 27.toBigInteger()).let {
            if (it == BigInteger.ZERO) " " else ('`' + it.toInt()).toString()
        }))
    }

    val decoded = cipher.fold(StringBuilder()) { acc, c ->
        val m = exponentiation(c, if (eInverse < BigInteger.ZERO) phi + eInverse else eInverse, n)
        acc.append(decode(m, StringBuilder()).reverse())
    }
    println(decoded.toString())
}

/**
 * @return (G, k, l) G = ka + lb
 */
fun extendedEuclidean(a: BigInteger, b: BigInteger): Triple<BigInteger, BigInteger, BigInteger> =
        if (a % b == BigInteger.ZERO)
            Triple(b, BigInteger.ZERO, BigInteger.ONE)
        else
            extendedEuclidean(b, a % b).let { (G, k, l) ->
                Triple(G, l, k - l * (a / b))
            }

fun exponentiation(a: BigInteger, e: BigInteger, n: BigInteger): BigInteger = with(generateSequence(a) { (it * it) % n }) {
    val str = e.toString(2)
    val iter = iterator()
    str.foldRight(BigInteger.ONE) { c, acc ->
        val exp = iter.next()
        (acc * (if (c == '1') exp else BigInteger.ONE)) % n
    }
}