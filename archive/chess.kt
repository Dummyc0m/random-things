import java.util.*

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)

    val r = sc.nextInt()

    val c = sc.nextInt()

    fun eq(a: Int, b: Int) : Boolean {
        return (r == a && c == b) || (c == a || r == b)
    }

    if (r <= 7 && c <= 7) {
        if (eq (2, 3) || eq (4, 6)) {
            println ("Britney")
        } else {
            println ("Alice")
        }
    } else {
        val ra = r - 4
        val ca = c - 4

    }

}
