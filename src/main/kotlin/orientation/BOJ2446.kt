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
 * *********
 *  *******
 *   *****
 *    ***
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

    with(bufferedWriter) {
        for (i in 1 until n) {
            for (j in 1 until i) {
                append(" ")
            }
            for (j in 1 until  (2 * n) - ((i * 2) - 2)) {
                append("*")
            }
            appendLine()
        }
        for (i in 1 until n) {
            append(" ")
        }
        append("*")
        appendLine()

        for (i in 1 until n) {
            for (j in 1 .. n - (i + 1)) {
                append(" ")
            }
            for (j in 1 .. (1 + (i * 2))) {
                append("*")
            }
            appendLine()
        }
    }

    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()
}