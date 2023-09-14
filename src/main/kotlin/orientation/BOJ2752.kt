package orientation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * 문제
 * 동규는 세수를 하다가 정렬이 하고 싶어졌다.
 *
 * 정수 세 개를 생각한 뒤에, 이를 오름차순으로 정렬하고 싶어졌다.
 *
 * 정수 세 개가 주어졌을 때, 가장 작은 수, 그 다음 수, 가장 큰 수를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 정수 세 개가 주어진다. 이 수는 1보다 크거나 같고, 1,000,000보다 작거나 같다. 이 수는 모두 다르다.
 *
 * 출력
 * 제일 작은 수, 그 다음 수, 제일 큰 수를 차례대로 출력한다.
 *
 * 예제 입력 1
 * 3 1 2
 * 예제 출력 1
 * 1 2 3
 */

fun main(arg: Array<String>) {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val firstLine = bufferedReader.readLine()
    val tokenizer = StringTokenizer(firstLine)

    fun compare(value1: Int, value2: Int): Int = if (value1 < value2) -1 else 1

    val linkedList = LinkedList<Int>().run {
        add(tokenizer.nextToken().toInt())
        add(tokenizer.nextToken().toInt())
        add(tokenizer.nextToken().toInt())
        sortedWith(::compare)
    }
    linkedList.forEach { bufferedWriter.append(it.toString()).append(" ") }
    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()
}