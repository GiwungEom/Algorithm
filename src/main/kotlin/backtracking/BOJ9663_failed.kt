package backtracking

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * 문제
 * N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
 *
 * N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. (1 ≤ N < 15)
 *
 * 출력
 * 첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.
 *
 * 예제 입력 1
 * 8
 * 예제 출력 1
 * 92
 */

fun main() {

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val n = bufferedReader.readLine().toInt()
    var cnt = 0
    val isUsed1 = BooleanArray(size = n)
    val isUsed2 = BooleanArray(size = 2 * n - 1)
    val isUsed3 = BooleanArray(size = 2 * n - 1)

    fun func(m: Int) {
        if (m == n) {
            cnt++
            return
        }

        for (y in 0 until n) {
            if (isUsed1[y] || isUsed2[m + y] || isUsed3[m - y + n - 1]) {
                continue
            }
            isUsed1[y] = true
            isUsed2[m + y] = true
            isUsed2[m - y + n - 1] = true
            func(m + 1)
            isUsed1[y] = false
            isUsed2[m + y] = false
            isUsed2[m - y + n - 1] = false
        }
    }

    func(0)
    bufferedWriter.append(cnt.toString()).flush()
    bufferedWriter.close()
    bufferedReader.close()
}