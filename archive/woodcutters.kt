import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

data class Tree(
        val loc: Int,
        val height: Int,
        val occupied: Boolean = false
)

data class TreeField(
        val length: Int,
        val trees: Array<Tree>,
        val treeLocs: List<Int>,
        val cutTrees: Int
) {

    // has to be a tree
    fun canCutLeft(loc: Int): Boolean {
        val tree = trees[loc]

        for (i in maxOf(0, loc - tree.height)..(loc - 1)) {
            if (trees[i].occupied) {
                return false
            }
        }
        return true
    }

    fun canCutRight(loc: Int): Boolean {
        val tree = trees[loc]

        for (i in (loc + 1)..minOf(trees.size - 1, loc + tree.height)) {
            if (trees[i].occupied) {
                return false
            }
        }
        return true
    }

    fun cutLeft(loc: Int): TreeField {
        val tree = trees[loc]
        val cutRange = ((loc - tree.height)..loc)
        val occupiedTrees = ArrayList<Int>()
        return TreeField(
                length,
                Array(trees.size) { i ->
                    val existing = trees[i]
                    if (i in cutRange) {
                        occupiedTrees.add(i)
                        existing.copy(occupied = true)
                    } else
                        existing
                },
                treeLocs.filter { !occupiedTrees.contains(it) },
                cutTrees + 1
        )
    }

    fun cutRight(loc: Int): TreeField {
        val tree = trees[loc]
        val cutRange = ((loc)..(loc + tree.height))
        val occupiedTrees = ArrayList<Int>()
        return TreeField(
                length,
                Array(trees.size) { i ->
                    val existing = trees[i]
                    if (i in cutRange) {
                        occupiedTrees.add(i)
                        existing.copy(occupied = true)
                    } else
                        existing
                },
                treeLocs.filter { !occupiedTrees.contains(it) },
                cutTrees + 1
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TreeField

        if (length != other.length) return false
        if (!Arrays.equals(trees, other.trees)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = length
        result = 31 * result + Arrays.hashCode(trees)
        return result
    }

}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)

    val n = sc.nextInt()
    sc.nextLine()

    val trees = LinkedList<Tree>()
    val trees_dup = LinkedList<Tree>()

    var maxSpot = 0

    for (i in 1..n) {
        val loc = sc.nextInt()
        val hei = sc.nextInt()
        val t = Tree(loc - 1, hei, true)
        trees.add(
                t
        )
        trees_dup.add(t)
        maxSpot = maxOf(maxSpot, loc)
    }

    var current: Tree? = trees.pop()

    val initialField = TreeField(maxSpot, Array(maxSpot) {
        val c = current
        if (c !== null) {
            if (it == c.loc) {
                if (trees.isNotEmpty())
                    current = trees.pop()
                c
            } else {
                Tree(it, 0, false)
            }
        } else {
            Tree(it, 0, false)
        }
    }, trees_dup.map { it.loc }, 0)

    // possible cuts
    val memoizer = HashMap<TreeField, Int>()

    fun solRec(field: TreeField): Int {
        return when {
            field.treeLocs.size == 0 -> 0
            else -> {
                field.treeLocs.map {
                    val canCutLeft = field.canCutLeft(it)
                    val canCutRight = field.canCutRight(it)
                    if (canCutLeft || canCutRight) {
                        val left = if (canCutLeft) {
                            val cutLeft = field.cutLeft(it)
                            memoizer[cutLeft] ?: solRec(cutLeft)
                        } else {
                            field.cutTrees
                        }
                        val right = if (canCutRight) {
                            val cutRight = field.cutRight(it)
                            memoizer[cutRight] ?: solRec(cutRight)
                        } else {
                            field.cutTrees
                        }

                        maxOf(left, right)
                    } else {
                        field.cutTrees
                    }
                }.max().let {
                    if (it == null) {
                        memoizer[field] = field.cutTrees
                        field.cutTrees
                    } else {
                        memoizer[field] = it
                        it
                    }
                }
            }
        }
    }

    println(solRec(initialField))
}