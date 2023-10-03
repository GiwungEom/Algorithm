package graph

class Test {

    fun pair_test() {
        (1 to 1) comp (2 to 2)
    }

    infix fun Pair<Int, Int>.comp(pair: Pair<Int, Int>): Int =
        if (first > pair.first) {
            if (second > pair.second) {
                1
            } else {
                -1
            }
        } else if (first == pair.first && second == pair.second) {
            0
        } else {
            -1
        }

}