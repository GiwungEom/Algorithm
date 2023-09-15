package orientation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * 문제
 * 첫째 줄에는 별 1개, 둘째 줄에는 별 3개, ..., N번째 줄에는 별 2×N-1개를 찍는 문제
 *
 * 별은 가운데를 기준으로 대칭이어야 한다.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 100)이 주어진다.
 *
 * 출력
 * 첫째 줄부터 N번째 줄까지 차례대로 별을 출력한다.
 *
 * 예제 입력 1
 * 5
 * 예제 출력 1
 *     *
 *    ***
 *   *****
 *  *******
 * *********
 */

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val n = bufferedReader.readLine().toInt()
    val starCount = n * 2 - 1
    val mid = starCount / 2

    for (i in 0 until n) {
        for (j in 0 until starCount) {
            if (j in (mid - i)..(mid + i)) {
                bufferedWriter.append("*")
            } else {
                bufferedWriter.append(" ")
            }
            if (j >= mid + i) break
        }
        bufferedWriter.appendLine()
    }
    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()
}