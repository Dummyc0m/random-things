import java.lang.NullPointerException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main (args: Array<String>) {
    val sc = Scanner(System.`in`)

    val k = sc.nextInt()
    val n = sc.nextInt()

    val occ = HashMap<Int, Int>(k * 2)

    for (i in 1..n) {
        val v = sc.nextInt()

        val curr = occ[v]
        if (curr == null) {
            occ[v] = 1
        } else {
            occ[v] = curr + 1
        }
    }

    val freq = HashMap<Int, ArrayList<Int>>(10)

    for (e in occ.entries) {
        val (key, value) = e

        val curr = freq[value]

        if (curr == null) {
            val arr = ArrayList<Int>()
            arr.add(key)
            freq[value] = arr
        } else {
            curr.add(key)
        }
    }

    if (freq.size > 3) {
        println("*")
    } else {
        when (freq.size) {
            1 -> {

            }
            2 -> {
                var max = 0
                var maxv = ArrayList<Int>()
                var min = Int.MAX_VALUE
                var minv = ArrayList<Int>()

                for ((k, v) in freq.entries) {
                    if (k > max) {
                        max = k
                        maxv = v
                    }

                    if (k < min) {
                        min = k
                        minv = v
                    }
                }
                if (max - min == 1) {
                    if (maxv.size > minv.size) {
                        if (minv.size == 1) {
                            println("+${minv[0]}")
                        } else {
                            println("*")
                        }
                    } else {
                        if (maxv.size == 1) {
                            println("-${maxv[0]}")
                        } else {
                            println("*")
                        }
                    }
                } else {
                    if (max - min == 2 && maxv.size == 1 && minv.size == 1) {
                        println("-${maxv[0]} +${minv[0]}")
                    } else {
                        if (min == 1 && minv.size == 1) {
                            println("-${minv[0]}")
                        } else if (max == 1 && maxv.size == 1) {
                            println("-${maxv[0]}")
                        }else
                        {
                            println("*")
                        }
                    }
                }
            }
            3 -> {
                var max = 0
                var maxv = ArrayList<Int>()
                var min = Int.MAX_VALUE
                var minv = ArrayList<Int>()

                for ((k, v) in freq.entries) {
                    if (k > max) {
                        max = k
                        maxv = v
                    }

                    if (k < min) {
                        min = k
                        minv = v
                    }
                }

                freq.remove(max)
                freq.remove(min)
                var (avg, other) = freq.entries.first()

                if (maxv.size == 1 && minv.size == 1) {
                    if (max == avg + 1 && min == avg - 1) {
                        println("-${maxv[0]} +${minv[0]}")
                    } else {
                        println("*")
                    }
                } else if (maxv.size == 1 && other.size == 1) {
                    println("*")
//                    if (max == avg + 1 && min == avg - 1) {
//                        println("-${maxv[0]} +${minv[0]}")
//                    } else {
//                    }
                } else if (minv.size == 1 && other.size == 1) {
                    println("*")
//                    if (max == avg + 1 && min == avg - 1) {
//                        println("-${maxv[0]} +${minv[0]}")
//                    } else {
//                    }
                } else {
                    println("*")
                }
            }
        }

    }

}

