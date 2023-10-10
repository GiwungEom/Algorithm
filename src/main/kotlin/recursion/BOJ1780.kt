package recursion

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer


/**
 * 문제
 * N×N크기의 행렬로 표현되는 종이가 있다. 종이의 각 칸에는 -1, 0, 1 중 하나가 저장되어 있다.
 * 우리는 이 행렬을 다음과 같은 규칙에 따라 적절한 크기로 자르려고 한다.
 *
 * 1. 만약 종이가 모두 같은 수로 되어 있다면 이 종이를 그대로 사용한다.
 * 2. (1)이 아닌 경우에는 종이를 같은 크기의 종이 9개로 자르고, 각각의 잘린 종이에 대해서 (1)의 과정을 반복한다.
 * 이와 같이 종이를 잘랐을 때, -1로만 채워진 종이의 개수, 0으로만 채워진 종이의 개수, 1로만 채워진 종이의 개수를 구해내는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 37, N은 3 의 k 승 꼴)이 주어진다. 다음 N개의 줄에는 N개의 정수로 행렬이 주어진다.
 *
 * 출력
 * 첫째 줄에 -1로만 채워진 종이의 개수를, 둘째 줄에 0으로만 채워진 종이의 개수를, 셋째 줄에 1로만 채워진 종이의 개수를 출력한다.
 *
 * 예제 입력 1
 * 9
 * 0 0 0 1 1 1 -1 -1 -1
 * 0 0 0 1 1 1 -1 -1 -1
 * 0 0 0 1 1 1 -1 -1 -1
 * 1 1 1 0 0 0 0 0 0
 * 1 1 1 0 0 0 0 0 0
 * 1 1 1 0 0 0 0 0 0
 * 0 1 -1 0 1 -1 0 1 -1
 * 0 -1 1 0 1 -1 0 1 -1
 * 0 1 -1 1 0 -1 0 1 -1
 * 예제 출력 1
 * 10
 * 12
 * 11
 */

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val a = bufferedReader.readLine().toInt()
    val board: Array<IntArray> = Array(size = a) { IntArray(size = a) }
    repeat(a) { x ->
        val tokenizer = StringTokenizer(bufferedReader.readLine())
        repeat(a) { y ->
            board[x][y] = tokenizer.nextToken().toInt()
        }
    }

    fun checkPager(startPos: Pair<Int, Int>, endPos: Pair<Int, Int>): Pair<Boolean, Int> {
        var prev = -2
        for (x in startPos.first until endPos.first) {
            for (y in startPos.second until endPos.second) {
                if (prev == -2) {
                    prev = board[x][y]
                    continue
                }

                if (prev != board[x][y]) {
                    return false to 0
                }
                prev = board[x][y]
            }
        }
        return true to prev
    }

    fun paper(n: Int, startPos: Pair<Int, Int>): Triple<Int, Int, Int> {
        // 같은 수 체크
        val result = checkPager(startPos = startPos, endPos = startPos.first + n to startPos.second + n)
        if (result.first) {
            // 계산
            val innerFunc = { index: Int -> if (index == result.second) 1 else 0 }
            return Triple(innerFunc(-1), innerFunc(0), innerFunc(1))
        } else {
            // 9등분
            val pos = n / 3
            val resultArray = Array(size = 9) { Triple(0,0,0) }
            resultArray[0] = paper(pos, startPos.first + 0 to startPos.second + 0)
            resultArray[1] = paper(pos, startPos.first + 0 to startPos.second + pos)
            resultArray[2] = paper(pos, startPos.first + 0 to startPos.second + (pos * 2))
            resultArray[3] = paper(pos, startPos.first + pos to startPos.second + 0)
            resultArray[4] = paper(pos, startPos.first + pos to startPos.second + pos)
            resultArray[5] = paper(pos, startPos.first + pos to startPos.second + (pos * 2))
            resultArray[6] = paper(pos, startPos.first + pos * 2 to startPos.second + 0)
            resultArray[7] = paper(pos, startPos.first + pos * 2 to startPos.second + pos)
            resultArray[8] = paper(pos, startPos.first + pos * 2 to startPos.second + (pos * 2))
            return Triple(
                resultArray.sumOf { it.first },
                resultArray.sumOf { it.second },
                resultArray.sumOf { it.third }
            )
        }
    }

    val result = paper(a, startPos = 0 to 0)

    with(bufferedWriter) {
        append(result.first.toString()).appendLine()
        append(result.second.toString()).appendLine()
        append(result.third.toString())
        flush()
        close()
    }
    bufferedReader.close()
}