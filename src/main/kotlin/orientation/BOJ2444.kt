package orientation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * 문제
 * 예제를 보고 규칙을 유추한 뒤에 별을 찍어 보세요.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 100)이 주어진다.
 *
 * 출력
 * 첫째 줄부터 2×N-1번째 줄까지 차례대로 별을 출력한다.
 *
 * 예제 입력 1
 * 5
 * 예제 출력 1
 *     *
 *    ***
 *   *****
 *  *******
 * *********
 *  *******
 *   *****
 *    ***
 *     *
 */

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val n = bufferedReader.readLine().toInt()
    val line = (2 * n - 1)
    val mid = line / 2 + 1

    for (i in 1 .. line) {
        val starCount = if (i <= mid) {
            2 * i - 1
        } else {
            2 * (line - (i - 1)) - 1
        }
        val spaceCount = if (i <= mid) {
            mid - i
        } else {
            i - mid
        }

        repeat(spaceCount) {
            bufferedWriter.append(" ")
        }
        repeat(starCount) {
            bufferedWriter.append("*")
        }
        bufferedWriter.appendLine()
    }
    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()
}