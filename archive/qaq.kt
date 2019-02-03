import java.util.*

fun main(args: Array<String>) {
    val line = Scanner(System.`in`).nextLine()
    val (QAQ, _, _) = numOf(line)
    println(QAQ)
}

fun numOf(str: String, char: Char): Int {
    return str.map { if (it == char) 1 else 0 }.sum()
}

// QAQ, QA, Q
fun numOf(str: String): Triple<Int, Int, Int> {
    return when {
        str.length == 0 -> Triple(0, 0, 0)
        str.length == 1 -> Triple(0, 0, numOf(str, 'Q'))
        str.length == 2 -> Triple(0, if (str == "QA") 1 else 0, numOf(str, 'Q'))
        else -> {
            val last = str.last()

            val head = str.dropLast(1)

            val (QAQ, QA, Q) = numOf(head)

            when (last) {
                'A' -> Triple(QAQ, QA + Q, Q)
                'Q' -> Triple(QAQ + QA, QA, Q + 1)
                else -> Triple(QAQ, QA, Q)
            }
        }
    }
}