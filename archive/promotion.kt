import java.util.*
import kotlin.collections.HashSet

fun main (args: Array<String>) {
    val sc = Scanner(System.`in`)
    val a = sc.nextInt()
    val b = sc.nextInt()

    val e = sc.nextInt()

    val p = sc.nextInt()

    val outgoing = Array<HashSet<Int>>(e) {HashSet()}
    val incoming = Array<HashSet<Int>>(e) {HashSet()}


    for (i in 1..p) {
        val x = sc.nextInt()
        val y = sc.nextInt()
        outgoing[x].add(y)
        incoming[y].add(x)
    }

    var processing = LinkedList<Int>()
    var next = LinkedList<Int>()

    val processed = HashSet<Int>()
    // i away from s
    var pc = 0;
    var lc = 0;

    val disconnected = ArrayList<Int>()

    for (i in 0..(incoming.size - 1)) {
        if (incoming[i].isEmpty()) {
            if (outgoing[i].isEmpty()) {
                disconnected.add(i)
            } else {
                processing.add(i)
            }
        }
    }

    var ares = 0;
    var bres = 0;

    while (processing.isNotEmpty()) {
        lc = pc
        while (processing.isNotEmpty()) {
            val curr = processing.poll()
            if (processed.contains(curr)) {
                continue
            }

            processed.add(curr)

            pc++

            val it = outgoing[curr].iterator()
            while (it.hasNext()) {
                val x = it.next()
                if (incoming[x].size == 1) {
                    next.add(x)
                } else {
                    incoming[x].remove(curr)
                    it.remove()
                }
            }
        }

        if (lc < a && a <= pc) {
            if (a == pc) {
                ares = pc
            } else {
                ares = lc
            }
        }

        if (lc < a && a <= pc) {
            if (a == pc) {
                bres = pc
            } else {
                bres = lc
            }
        }

        val curr = processing
        processing = next
        next = curr
        next.clear()
    }

    println(ares)
    println(bres)
    println(e - pc)


}