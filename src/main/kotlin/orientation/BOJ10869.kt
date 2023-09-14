package orientation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * 문제
 * 두 자연수 A와 B가 주어진다. 이때, A+B, A-B, A*B, A/B(몫), A%B(나머지)를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 두 자연수 A와 B가 주어진다. (1 ≤ A, B ≤ 10,000)
 *
 * 출력
 * 첫째 줄에 A+B, 둘째 줄에 A-B, 셋째 줄에 A*B, 넷째 줄에 A/B, 다섯째 줄에 A%B를 출력한다.
 *
 * 예제 입력 1
 * 7 3
 * 예제 출력 1
 * 10
 * 4
 * 21
 * 2
 * 1
 */
fun main(arg: Array<String>) {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    bufferedReader.readLine().also {
        val tokenizer = StringTokenizer(it)
        val a = tokenizer.nextToken().toInt()
        val b = tokenizer.nextToken().toInt()
        bufferedWriter.apply {
            append((a + b).toString()).appendLine()
            append((a - b).toString()).appendLine()
            append((a * b).toString()).appendLine()
            append((a / b).toString()).appendLine()
            append((a % b).toString()).appendLine()
    }}
    bufferedWriter.flush()
    bufferedReader.close()
    bufferedWriter.close()
}