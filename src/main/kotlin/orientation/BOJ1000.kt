package orientation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

/**
 * 문제
 * 두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 A와 B가 주어진다. (0 < A, B < 10)
 *
 * 출력
 * 첫째 줄에 A+B를 출력한다.
 *
 * 예제 입력 1
 * 1 2
 * 예제 출력 1
 * 3
 */
fun main(arg: Array<String>) {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val firstLine = bufferedReader.readLine()
    val tokenizer = StringTokenizer(firstLine)
    val calc = tokenizer.nextToken().toInt() + tokenizer.nextToken().toInt()
    bufferedWriter.append(calc.toString()).flush()
}