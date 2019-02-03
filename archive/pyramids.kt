import java.util.*

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)

    val l3 = sc.nextInt()

    val l4 = sc.nextInt()

    val l5 = sc.nextInt()

    val l5h = l5 / Math.sin(Math.toRadians(72.0)) * Math.sin(Math.toRadians(54.0))

    val l5v = 5.0 / 12.0 * Math.tan(Math.toRadians(54.0)) * (Math.sqrt(l5 * l5 - (l5h * l5h))) * l5 * l5

    val l4h = Math.sin(Math.toRadians(45.0)) * l4

    val l4v = l4 * l4 * (Math.sqrt(l4 * l4 - l4h * l4h)) / 3.0

    val l3h = l3 / Math.sin(Math.toRadians(120.0)) * Math.sin(Math.toRadians(30.0))

    val l3v = (Math.sqrt(3.0) / 4.0 * l3 * l3) * (Math.sqrt(l3 * l3 - l3h * l3h)) / 3.0

    println(l5v + l4v + l3v)
}