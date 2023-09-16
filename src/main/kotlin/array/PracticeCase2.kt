package array

/**
 * 길이 N 배열 합이 100인 서로다른 두 원소 존재시 1, 아니면 0
 * 반환 함수 func2(int arr[], int N) 을 작성하라.
 * arr 수는 0 <= 요소 <= 100, N은 1000 이하
 */
fun main() {
    val input = intArrayOf(4, 13, 63, 87)

    val numbers = IntArray(101)
    var found = false

//    시간복잡도 2N
//    input.forEach {
//        numbers[it] = it
//    }
//    input.forEach {
//        if (numbers[100 - it] != 0) found = true
//    }

//    시간복잡도 N
    input.forEach {
        numbers[it] = 1
        if (numbers[100 - it] == 1) found = true
    }

    println("found : $found")
}