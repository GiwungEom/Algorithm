package graph.bfs

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * 문제
 * 적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다. 따라서, 적록색약인 사람이 보는 그림은 아닌 사람이 보는 그림과는 좀 다를 수 있다.
 *
 * 크기가 N×N인 그리드의 각 칸에 R(빨강), G(초록), B(파랑) 중 하나를 색칠한 그림이 있다. 그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다.
 * 또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다. (색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다)
 *
 * 예를 들어, 그림이 아래와 같은 경우에
 *
 * RRRBB
 * GGBBB
 * BBBRR
 * BBRRR
 * RRRRR
 * 적록색약이 아닌 사람이 봤을 때 구역의 수는 총 4개이다. (빨강 2, 파랑 1, 초록 1) 하지만, 적록색약인 사람은 구역을 3개 볼 수 있다. (빨강-초록 2, 파랑 1)
 *
 * 그림이 입력으로 주어졌을 때, 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. (1 ≤ N ≤ 100)
 *
 * 둘째 줄부터 N개 줄에는 그림이 주어진다.
 *
 * 출력
 * 적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때의 구역의 수를 공백으로 구분해 출력한다.
 *
 * 예제 입력 1
 * 5
 * RRRBB
 * GGBBB
 * BBBRR
 * BBRRR
 * RRRRR
 * 예제 출력 1
 * 4 3
 */

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val input = bufferedReader.readLine()
    var tokenizer = StringTokenizer(input)
    val n = tokenizer.nextToken().toInt()

    val board = Array(size = n) { CharArray(size = n) }
    val colorRgbVisit = Array(size = n) { Array(size = n) { false } }
    val colorBlindnessVisit = Array(size = n) { Array(size = n) { false } }

    repeat(n) { x ->
        val colors = bufferedReader.readLine()
        repeat(n) { y ->
            board[x][y] = colors.elementAt(y)
        }
    }

    val compareCase1: (Char, Char) -> Boolean = { color1, color2 -> color1 == color2 }
    val compareCase2: (Char, Char) -> Boolean = { color1, color2 ->
        if (('R' == color1 || 'G' == color1) && ('R' == color2 || 'G' == color2)) {
            true
        } else {
            color1 == 'B' && color2 == 'B'
        }
    }

    fun colorBfs(
        visit: Array<Array<Boolean>>,
        compareCase: (Char, Char) -> Boolean
    ): Int {
        var areaCount = 0
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        val dx = intArrayOf(1, 0, -1, 0)
        val dy = intArrayOf(0, 1, 0, -1)

        repeat(n) { row ->
            repeat(n) { column ->
                if (!visit[row][column]) {
                    areaCount += 1

                    val startColor = board[row][column]
                    queue.add(row to column)
                    visit[row][column] = true

                    while (queue.isNotEmpty()) {
                        val cur = queue.remove()

                        for (i in 0..3) {
                            val next = cur.first + dx[i] to cur.second + dy[i]

                            if (next.first < 0 || next.first >= n || next.second < 0 || next.second >= n) continue
                            if (!compareCase(startColor, board[next.first][next.second]) || visit[next.first][next.second]) continue

                            visit[next.first][next.second] = true
                            queue.add(next)
                        }
                    }
                }
            }
        }
        return areaCount
    }

    val rgbAreaCount = colorBfs(colorRgbVisit, compareCase1)
    val colorBlindnessCount = colorBfs(colorBlindnessVisit, compareCase2)

    bufferedWriter.apply {
        append("$rgbAreaCount $colorBlindnessCount")
        flush()
        close()
    }
    bufferedReader.close()
}