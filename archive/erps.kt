import java.util.*

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)

    val t = sc.nextInt()
    sc.nextLine()

    val win = t / 2

    for (i in 1..t) {
        for (w in 1..win) {
            val q = w - 1
            println("$i ${(i + q) % t + 1}")
        }
    }
}
