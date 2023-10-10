package recursion

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * 문제
 * 재귀적인 패턴으로 별을 찍어 보자. N이 3의 거듭제곱(3, 9, 27, ...)이라고 할 때, 크기 N의 패턴은 N×N 정사각형 모양이다.
 *
 * 크기 3의 패턴은 가운데에 공백이 있고, 가운데를 제외한 모든 칸에 별이 하나씩 있는 패턴이다.
 *
 * ***
 * * *
 * ***
 * N이 3보다 클 경우, 크기 N의 패턴은 공백으로 채워진 가운데의 (N/3)×(N/3) 정사각형을 크기 N/3의 패턴으로 둘러싼 형태이다. 예를 들어 크기 27의 패턴은 예제 출력 1과 같다.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. N은 3의 거듭제곱이다. 즉 어떤 정수 k에 대해 N=3k이며, 이때 1 ≤ k < 8이다.
 *
 * 출력
 * 첫째 줄부터 N번째 줄까지 별을 출력한다.
 *
 * 예제 입력 1
 * 27
 * 예제 출력 1
 * ***************************
 * * ** ** ** ** ** ** ** ** *
 * ***************************
 * ***   ******   ******   ***
 * * *   * ** *   * ** *   * *
 * ***   ******   ******   ***
 * ***************************
 * * ** ** ** ** ** ** ** ** *
 * ***************************
 * *********         *********
 * * ** ** *         * ** ** *
 * *********         *********
 * ***   ***         ***   ***
 * * *   * *         * *   * *
 * ***   ***         ***   ***
 * *********         *********
 * * ** ** *         * ** ** *
 * *********         *********
 * ***************************
 * * ** ** ** ** ** ** ** ** *
 * ***************************
 * ***   ******   ******   ***
 * * *   * ** *   * ** *   * *
 * ***   ******   ******   ***
 * ***************************
 * * ** ** ** ** ** ** ** ** *
 * ***************************
 */

fun main() {

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val n = bufferedReader.readLine().toInt()
    val board = Array(size = n) { BooleanArray(size = n) } // false - empty, true - star

    fun putStars(div: Int, startPos: Pair<Int, Int>, star: Boolean) {
        for (x in startPos.first until startPos.first + div) {
            for (y in startPos.second until startPos.second + div) {
                board[x][y] = star
            }
        }
    }

    fun calcStars(length: Int, startPos: Pair<Int, Int>) {

        if (length == 1) {
            putStars(length, startPos, true)
            return
        }

        val div = length / 3
        val func = { index: Int -> if (index == 1) 0 else if (index == 2) div else div * 2 }

        for (x in 1..3) {
            for (y in 1..3) {
                if (x == 2 && y == 2) {
                    putStars(div, startPos.first + func(x) to startPos.second + func(y), false)
                } else {
                    calcStars(div, startPos.first + func(x) to startPos.second + func(y))
                }
            }
        }
    }

    fun printStars() {
        board.forEachIndexed { x, booleans ->
            booleans.forEachIndexed { y, boolean ->
                if (board[x][y]) bufferedWriter.append("*") else bufferedWriter.append(" ")
            }
            bufferedWriter.appendLine()
        }
        bufferedWriter.flush()
    }

    calcStars(n, 0 to 0)
    printStars()
    bufferedWriter.close()
    bufferedReader.close()
}

