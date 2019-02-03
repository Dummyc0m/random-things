import java.util.*

fun main (args: Array<String>) {
    val sc = Scanner(System.`in`)
    // junctions
    val n = sc.nextInt()
    // bi-roads
    val m = sc.nextInt()

    // start
    val s = sc.nextInt()

    // end
    val t = sc.nextInt()

    val alist = Array<ArrayList<Int>>(n + 1) {ArrayList()}

    for (i in 1..m) {
        val u = sc.nextInt()
        val v = sc.nextInt()
        alist[u].add(v)
        alist[v].add(u)
    }

        var processing = LinkedList<Int>()
        var next = LinkedList<Int>()

        var processed = HashSet<Int>()
        var d = 0
        var st = 0
    // i away from s
        val sorted = ArrayList<Int>()

    var pc = 0
        processing.offer(s)
        while (processing.isNotEmpty()) {
            while (processing.isNotEmpty()) {
                val curr = processing.poll()
                if (processed.contains(curr)) {
                    continue
                }

                if (curr == t) {
                    st = d
                }
                pc++

                processed.add(curr)
                next.addAll(alist[curr])
            }
            d++
            sorted.add(pc)
            pc = 0

            val curr = processing
            processing = next
            next = curr
            next.clear()
        }

    processing.clear()
    next.clear()

    processed.clear()

    // i away from t
    val sorted2 = ArrayList<Int>()
    pc = 0

    processing.offer(s)
    while (processing.isNotEmpty()) {
        while (processing.isNotEmpty()) {
            val curr = processing.poll()
            if (processed.contains(curr)) {
                continue
            }

            pc++

            processed.add(curr)
            next.addAll(alist[curr])
        }
        sorted2.add(pc)

        val curr = processing
        processing = next
        next = curr
        next.clear()
    }

    var illegal = 0
    for (i in 0..(st-2)) {
        val counter = st - 2 - i
        illegal += sorted[i] * sorted2[counter]
    }

    println(n * (n - 1) / 2 - m - illegal)
}