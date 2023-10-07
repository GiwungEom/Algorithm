package recursion

fun main() {

    fun subraction(n: Int): Int {
        println("n : $n")
        if (n == 1) return 1
        return subraction(n - 1)
    }
    subraction(5)
}

