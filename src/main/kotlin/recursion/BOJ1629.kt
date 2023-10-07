package recursion

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * 문제
 * 자연수 A를 B번 곱한 수를 알고 싶다. 단 구하려는 수가 매우 커질 수 있으므로 이를 C로 나눈 나머지를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 A, B, C가 빈 칸을 사이에 두고 순서대로 주어진다. A, B, C는 모두 2,147,483,647 이하의 자연수이다.
 *
 * 출력
 * 첫째 줄에 A를 B번 곱한 수를 C로 나눈 나머지를 출력한다.
 *
 * 예제 입력 1
 * 10 11 12
 * 예제 출력 1
 * 4
 */

fun main() {

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val tokenizer = StringTokenizer(bufferedReader.readLine())
    val a = tokenizer.nextToken().toLong()
    val b = tokenizer.nextToken().toLong()
    val c = tokenizer.nextToken().toLong()

    fun moduloCalc(exponent: Long): Long {
        if (exponent == 1L) return a % c

        var value = moduloCalc(exponent / 2) % c
        value = value * value % c
        if (exponent % 2 == 1L) {
            value = value * a % c
        }
        return value
    }

    val value = moduloCalc(b)
    bufferedWriter.append(value.toString()).flush()
    bufferedWriter.close()
    bufferedReader.close()
}