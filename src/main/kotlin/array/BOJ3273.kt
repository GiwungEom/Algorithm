package array

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * 문제
 * n개의 서로 다른 양의 정수 a1, a2, ..., an으로 이루어진 수열이 있다. ai의 값은 1보다 크거나 같고, 1000000보다 작거나 같은 자연수이다.
 * 자연수 x가 주어졌을 때, ai + aj = x (1 ≤ i < j ≤ n)을 만족하는 (ai, aj)쌍의 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 수열의 크기 n이 주어진다. 다음 줄에는 수열에 포함되는 수가 주어진다. 셋째 줄에는 x가 주어진다. (1 ≤ n ≤ 100000, 1 ≤ x ≤ 2000000)
 *
 * 출력
 * 문제의 조건을 만족하는 쌍의 개수를 출력한다.
 *
 * 예제 입력 1
 * 9
 * 5 12 7 10 9 1 2 3 11
 * 13
 * 예제 출력 1
 * 3
 */

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val n = bufferedReader.readLine().toInt()
    val numberColumn = bufferedReader.readLine()
    val x = bufferedReader.readLine().toInt()

    val numbers = IntArray(2000001)
    var resultCount = 0

    val tokenizer = StringTokenizer(numberColumn)
    repeat(n) {
        val ai = tokenizer.nextToken().toInt()
        if (x - ai > 0 && numbers[x - ai] == 1) {
            resultCount++
        }
        numbers[ai] = 1
    }
    bufferedWriter.append(resultCount.toString())

    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()
}