package orientation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * 문제
 * 9개의 서로 다른 자연수가 주어질 때, 이들 중 최댓값을 찾고 그 최댓값이 몇 번째 수인지를 구하는 프로그램을 작성하시오.
 *
 * 예를 들어, 서로 다른 9개의 자연수
 *
 * 3, 29, 38, 12, 57, 74, 40, 85, 61
 *
 * 이 주어지면, 이들 중 최댓값은 85이고, 이 값은 8번째 수이다.
 *
 * 입력
 * 첫째 줄부터 아홉 번째 줄까지 한 줄에 하나의 자연수가 주어진다. 주어지는 자연수는 100 보다 작다.
 *
 * 출력
 * 첫째 줄에 최댓값을 출력하고, 둘째 줄에 최댓값이 몇 번째 수인지를 출력한다.
 *
 * 예제 입력 1
 * 3
 * 29
 * 38
 * 12
 * 57
 * 74
 * 40
 * 85
 * 61
 * 예제 출력 1
 * 85
 * 8
 */

fun main() {

    data class Elements(val index: Int = 0, val data: Int = 0)

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val array = Array(9) { Elements() }

    repeat(9) {
        val n = bufferedReader.readLine().toInt()
        array[it] = Elements(index = it, data = n)
    }
    array.sortWith { elements1, elements2 ->
        if (elements1.data < elements2.data) 1 else -1
    }

    bufferedWriter.append(array.first().data.toString()).appendLine()
    bufferedWriter.append((array.first().index + 1).toString())

    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()
}