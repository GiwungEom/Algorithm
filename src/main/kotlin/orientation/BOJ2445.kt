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
 * *        *
 * **      **
 * ***    ***
 * ****  ****
 * **********
 * ****  ****
 * ***    ***
 * **      **
 * *        *
 */

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val n = bufferedReader.readLine().toInt()
    val line = (2 * n - 1)
    val mid = line / 2
    val maxStarCount = n * 2
    val halfStarCount = maxStarCount / 2

    val starMatrix = Array(line) { IntArray(maxStarCount) }
    for (i in 0 until line) {
        val halfArray = IntArray(halfStarCount)
        if (i <= mid) {
            for (j in 0 until halfStarCount) {
                if (j <= i) {
                    halfArray[j] = 1   // star
                } else {
                    halfArray[j] = 0   // space
                }
            }
        } else {
            for (j in 0 until halfStarCount) {
                if (j <= mid - (i - mid)) { // i = 5, mid = 4
                    halfArray[j] = 1   // star
                } else {
                    halfArray[j] = 0   // space
                }
            }
        }
        with(halfArray) {
            copyInto(starMatrix[i], 0)
            reverse()
            copyInto(starMatrix[i], size)
        }
    }

    with(bufferedWriter) {
        starMatrix.forEach { stars ->
            stars.forEach {
                append(
                    if (it == 1) "*" else " "
                )
            }
            appendLine()
        }
    }

    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()
}