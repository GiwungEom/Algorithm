package backtracking

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * 문제
 * N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오. N개의 자연수는 모두 다른 수이다.
 *
 * N개의 자연수 중에서 M개를 고른 수열
 * 고른 수열은 오름차순이어야 한다.
 * 입력
 * 첫째 줄에 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
 *
 * 둘째 줄에 N개의 수가 주어진다. 입력으로 주어지는 수는 10,000보다 작거나 같은 자연수이다.
 *
 * 출력
 * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
 *
 * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
 *
 * 예제 입력 1
 * 3 1
 * 4 5 2
 * 예제 출력 1
 * 2
 * 4
 * 5
 * 예제 입력 2
 * 4 2
 * 9 8 7 1
 * 예제 출력 2
 * 1 7
 * 1 8
 * 1 9
 * 7 8
 * 7 9
 * 8 9
 * 예제 입력 3
 * 4 4
 * 1231 1232 1233 1234
 * 예제 출력 3
 * 1231 1232 1233 1234
 */

fun main() {

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))
    var tokenizer = StringTokenizer(bufferedReader.readLine())

    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()
    val arr = IntArray(size = n)
    val board = LinkedList<Int>().apply { sortWith { a: Int, b: Int -> a - b } }
    val isUsed = BooleanArray(size = n)

    tokenizer = StringTokenizer(bufferedReader.readLine())

    repeat(n) {
        board.add(tokenizer.nextToken().toInt())
    }
    board.sortWith { a: Int, b: Int -> a - b }

    fun func(k: Int, j: Int) {
        if (k == m) {
            for (i in 0 until m) {
                bufferedWriter.append(arr[i].toString()).append(" ")
            }
            bufferedWriter.appendLine()
            return
        }

        for (i in j until n) {
            if (!isUsed[i]) {
                isUsed[i] = true
                arr[k] = board[i]
                func(k + 1, i + 1)
                isUsed[i] = false
            }
        }
    }
    func(0, 0)
    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()
}