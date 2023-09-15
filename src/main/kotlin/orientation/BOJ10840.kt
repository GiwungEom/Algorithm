package orientation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * 문제
 * 1부터 20까지 숫자가 하나씩 쓰인 20장의 카드가 아래 그림과 같이 오름차순으로 한 줄로 놓여있다. 각 카드의 위치는 카드 위에 적힌 숫자와 같이 1부터 20까지로 나타낸다.
 *
 *  	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19	20
 * 카드	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19	20
 * 이제 여러분은 다음과 같은 규칙으로 카드의 위치를 바꾼다: 구간 [a, b] (단, 1 ≤ a ≤ b ≤ 20)가 주어지면 위치 a부터 위치 b까지의 카드를 현재의 역순으로 놓는다.
 *
 * 예를 들어, 현재 카드가 놓인 순서가 위의 그림과 같고 구간이 [5, 10]으로 주어진다면, 위치 5부터 위치 10까지의 카드 5, 6, 7, 8, 9, 10을 역순으로 하여 10, 9, 8, 7, 6, 5로 놓는다. 이제 전체 카드가 놓인 순서는 아래 그림과 같다.
 *
 *  	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19	20
 * 카드	1	2	3	4	10	9	8	7	6	5	11	12	13	14	15	16	17	18	19	20
 * 이 상태에서 구간 [9, 13]이 다시 주어진다면, 위치 9부터 위치 13까지의 카드 6, 5, 11, 12, 13을 역순으로 하여 13, 12, 11, 5, 6으로 놓는다. 이제 전체 카드가 놓인 순서는 아래 그림과 같다.
 *
 *  	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19	20
 * 카드	1	2	3	4	10	9	8	7	13	12	11	5	6	14	15	16	17	18	19	20
 * 오름차순으로 한 줄로 놓여있는 20장의 카드에 대해 10개의 구간이 주어지면, 주어진 구간의 순서대로 위의 규칙에 따라 순서를 뒤집는 작업을 연속해서 처리한 뒤 마지막 카드들의 배치를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 총 10개의 줄에 걸쳐 한 줄에 하나씩 10개의 구간이 주어진다. i번째 줄에는 i번째 구간의 시작 위치 ai와 끝 위치 bi가 차례대로 주어진다. 이때 두 값의 범위는 1 ≤ ai ≤ bi ≤ 20이다.
 *
 * 출력
 * 1부터 20까지 오름차순으로 놓인 카드들에 대해, 입력으로 주어진 10개의 구간 순서대로 뒤집는 작업을 했을 때 마지막 카드들의 배치를 한 줄에 출력한다.
 *
 * 예제 입력 1
 * 5 10
 * 9 13
 * 1 2
 * 3 4
 * 5 6
 * 1 2
 * 3 4
 * 5 6
 * 1 20
 * 1 20
 * 예제 출력 1
 * 1 2 3 4 10 9 8 7 13 12 11 5 6 14 15 16 17 18 19 20
 * 예제 입력 2
 * 1 1
 * 2 2
 * 3 3
 * 4 4
 * 5 5
 * 6 6
 * 7 7
 * 8 8
 * 9 9
 * 10 10
 * 예제 출력 2
 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
 * 예제 입력 3
 * 1 20
 * 2 19
 * 3 18
 * 4 17
 * 5 16
 * 6 15
 * 7 14
 * 8 13
 * 9 12
 * 10 11
 * 예제 출력 3
 * 20 2 18 4 16 6 14 8 12 10 11 9 13 7 15 5 17 3 19 1
 */

fun main(arg: Array<String>) {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val cardArr = IntArray(20) { i -> i + 1 }
    val rangeArr = ArrayList<Pair<Int, Int>>(10)

    repeat(10) {
        val inputLine = bufferedReader.readLine()
        val tokenizer = StringTokenizer(inputLine)
        rangeArr.add(Pair(tokenizer.nextToken().toInt(), tokenizer.nextToken().toInt()))
    }

    rangeArr.forEach {
        cardArr.reverse(it.first - 1, it.second)
    }

    bufferedWriter.write(cardArr.joinToString(separator = " "))
    bufferedWriter.close()
    bufferedReader.close()
}


