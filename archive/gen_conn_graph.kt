import java.security.SecureRandom
import java.util.*

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)

    /*
    n: number of nodes
    maxW: maximum weight
    numOfEdges: numOfEdges between n - 1 and n(n-1)/2
     */
    val n = sc.nextInt()
    val maxW = sc.nextInt() + 1
    val numOfEdges = Integer.min (sc.nextInt(), n * (n - 1) / 2)

    val adjMat = Array(n) { Array(n) { -1 } }

    val rand = Random()

    for (i in 0 until n) {
        if (i == 0) {
            continue
        }
        val connectTo = rand.nextInt(i)

        val weight = rand.nextInt(maxW)

        adjMat[i][connectTo] = weight
        adjMat[connectTo][i] = weight
    }

    var toAdd = numOfEdges - n + 1


    while (toAdd > 0) {
        val from = rand.nextInt(n)
        val to = rand.nextInt(n)

        if (adjMat[from][to] != -1) {
            continue
        }

        if (from == to) {
            continue
        }

        val weight = rand.nextInt(maxW)

        adjMat[from][to] = weight
        adjMat[to][from] = weight
        toAdd--;
    }


    val num = rand.nextInt(1000000000)
    val sb = StringBuilder("val graph$num = (1,[")

    for (i in 0 until n) {
        for (j in i until n) {
            if (i == j) continue

            if (adjMat[i][j] == -1) continue
            sb.append("($i,$j,${adjMat[i][j]}),")
        }
    }

    sb.deleteCharAt(sb.lastIndex)

    sb.append("],$n)")

    println(sb.toString())
}