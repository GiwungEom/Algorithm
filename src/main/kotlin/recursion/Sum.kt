package recursion

fun main() {

    fun sum(e: Int, n: Int): Int {
        if (e == n) return n
        return e + sum(e + 1, n)
    }

    println(sum(1, 5))
}