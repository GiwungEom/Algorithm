package recursion

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * 문제
 * 예제를 보고 규칙을 유추한 뒤에 별을 찍어 보세요.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. N은 항상 3×2k 수이다. (3, 6, 12, 24, 48, ...) (0 ≤ k ≤ 10, k는 정수)
 *
 * 출력
 * 첫째 줄부터 N번째 줄까지 별을 출력한다.
 *
 * 예제 입력 1
 * 24
 * 예제 출력 1
 *                        *
 *                       * *
 *                      *****
 *                     *     *
 *                    * *   * *
 *                   ***** *****                // 2 * n - 1
 *                  *           *
 *                 * *         * *
 *                *****       *****
 *               *     *     *     *
 *              * *   * *   * *   * *
 *             ***** ***** ***** *****
 *            *                       *
 *           * *                     * *
 *          *****                   *****
 *         *     *                 *     *
 *        * *   * *               * *   * *
 *       ***** *****             ***** *****
 *      *           *           *           *
 *     * *         * *         * *         * *
 *    *****       *****       *****       *****
 *   *     *     *     *     *     *     *     *
 *  * *   * *   * *   * *   * *   * *   * *   * *
 * ***** ***** ***** ***** ***** ***** ***** *****
 *
 */

fun main() {
    // 3 6 12 24 48
    // 3 x (2 의 k 승) (0 ≤ k ≤ 10)
    // 재귀 n = 1 ,n = k, n = k + 1

    // 3 x 3, 6 x 6

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val n = bufferedReader.readLine().toInt()
    val board: Array<IntArray> = Array(size = n) { IntArray(size = 2 * n - 1) } // 0 empty, 1 star

    fun putTriangle(middlePos: Pair<Int, Int>) {
        board[middlePos.first][middlePos.second] = 1
        board[middlePos.first + 1][middlePos.second - 1] = 1
        board[middlePos.first + 1][middlePos.second + 1] = 1
        for (i in -2..2) {
            board[middlePos.first + 2][middlePos.second + i] = 1
        }
    }

    fun calcStar(length: Int, middlePos: Pair<Int, Int>) {
        if (length == 3) {
            putTriangle(middlePos)
            return
        }

        val div = length / 2
        calcStar(div, middlePos.first to middlePos.second)
        calcStar(div, middlePos.first + div to middlePos.second - div)
        calcStar(div, middlePos.first + div to middlePos.second + div)
    }

    fun printStar() {
        board.forEachIndexed { x, ints ->
            ints.forEachIndexed { y, _ ->
                if (board[x][y] == 1) bufferedWriter.append("*") else bufferedWriter.append(" ")
            }
            bufferedWriter.appendLine()
        }
    }

    calcStar(n, 0 to (n * 2 - 1) / 2)
    printStar()
    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()

}















