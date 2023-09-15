package orientation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

/**
 * 문제
 * 두 양의 정수가 주어졌을 때, 두 수 사이에 있는 정수를 모두 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 두 정수 A와 B가 주어진다.
 *
 * 출력
 * 첫째 줄에 두 수 사이에 있는 수의 개수를 출력한다.
 *
 * 둘째 줄에는 두 수 사이에 있는 수를 오름차순으로 출력한다.
 *
 * 서브태스크
 * 번호	배점	제한
 * 1	30
 * 1 ≤ A, B ≤ 1000.
 *
 * 2	70
 * 1 ≤ A, B ≤ 10 의 15승, A와 B의 차이는 최대 100,000.
 *
 * 예제 입력 1
 * 8 14
 * 예제 출력 1
 * 5
 * 9 10 11 12 13
 */

fun main(arg: Array<String>) {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val firstLine = bufferedReader.readLine()
    val tokenizer = StringTokenizer(firstLine)
    var a = tokenizer.nextToken().toLong()
    var b = tokenizer.nextToken().toLong()
    if (a > b) {
        val temp = a
        a = b
        b = temp
    }
    if (a == b || a - b == 1L) {
        bufferedWriter.append("0")
    } else {
        bufferedWriter.append((b - a - 1).toString()).appendLine()
        for (i in a+1 until b) {
            bufferedWriter.append(i.toString()).append(" ")
        }
    }

    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()
}