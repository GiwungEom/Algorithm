package graph.bfs

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * 문제
 * N×M크기의 배열로 표현되는 미로가 있다.
 *
 * 1	0	1	1	1	1
 * 1	0	1	0	1	0
 * 1	0	1	0	1	1
 * 1	1	1	0	1	1
 * 미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 이러한 미로가 주어졌을 때,
 * (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오.
 * 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.
 *
 * 위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.
 *
 * 입력
 * 첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.
 *
 * 출력
 * 첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
 *
 * 예제 입력 1
 * 4 6
 * 101111
 * 101010
 * 101011
 * 111011
 * 예제 출력 1
 * 15
 * 예제 입력 2
 * 4 6
 * 110110
 * 110110
 * 111111
 * 111101
 * 예제 출력 2
 * 9
 * 예제 입력 3
 * 2 25
 * 1011101110111011101110111
 * 1110111011101110111011101
 * 예제 출력 3
 * 38
 * 예제 입력 4
 * 7 7
 * 1011111
 * 1110001
 * 1000001
 * 1000001
 * 1000001
 * 1000001
 * 1111111
 * 예제 출력 4
 * 13
 */

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val input = bufferedReader.readLine()
    val tokenizer = StringTokenizer(input)
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

    val board = Array(size = n) { IntArray(size = m) }
    val visit = Array(size = n) { IntArray(size = m) { -1 } }
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    repeat(n) { i ->
        val drawing = bufferedReader.readLine()
        repeat(drawing.length) { j ->
            board[i][j] = drawing.elementAt(j).digitToInt()
        }
    }

    val startIndex = 0 to 0
    visit[startIndex.first][startIndex.second] = 1
    queue.add(startIndex)

    while (queue.isNotEmpty()) {
        val cur = queue.remove()

        for (i in 0..3) {
            val nextCur = cur.first + dx[i] to cur.second + dy[i]

            if (nextCur.first < 0 || nextCur.first >= n || nextCur.second < 0 || nextCur.second >= m) continue
            if (visit[nextCur.first][nextCur.second] != -1 || board[nextCur.first][nextCur.second] == 0) continue

            visit[nextCur.first][nextCur.second] = visit[cur.first][cur.second] + 1
            queue.add(nextCur)
        }

        if (visit[n-1][m-1] != -1) break
    }

    bufferedWriter.apply {
        append(visit[n-1][m-1].toString())
        flush()
        close()
    }
    bufferedReader.close()
}




