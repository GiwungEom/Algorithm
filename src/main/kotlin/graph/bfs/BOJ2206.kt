package graph.bfs

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * 문제
 * N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다.
 * 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다.
 * 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.
 *
 * 만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.
 *
 * 한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
 *
 * 맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.
 *
 * 출력
 * 첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.
 *
 * 예제 입력 1
 * 6 4
 * 0000
 * 1110
 * 1000
 * 0000
 * 1111
 * 0000
 * 예제 출력 1
 * 15
 * 예제 입력 2
 * 4 4
 * 0111
 * 1111
 * 1111
 * 1110
 * 예제 출력 2
 * -1
 */

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    var tokenizer = StringTokenizer(bufferedReader.readLine())
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

    val board: Array<CharArray> = Array(size = n) { CharArray(size = m) }
    val visit: Array<Array<IntArray>> = Array(size = n) { Array(size = m) { IntArray(size = 2) { -1 } } }
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
    repeat(n) { x ->
        bufferedReader.readLine().forEachIndexed { y, c ->
            board[x][y] = c
        }
    }

    fun bfsTry(): Int {
        queue.add(Triple(0, 0, 0))
        visit[0][0][0] = 1

        while (queue.isNotEmpty()) {
            val cur = queue.remove()

            for (i in 0..3) {
                val next = Triple(cur.first + dx[i], cur.second + dy[i], cur.third)
                val nextDist = visit[cur.first][cur.second][cur.third] + 1
                if (next.first == n - 1 && next.second == m - 1) return nextDist
                if (next.first < 0 || next.first >= n || next.second < 0 || next.second >= m) continue

                // 벽이 아닌 상태
                if (board[next.first][next.second] == '0' && visit[next.first][next.second][next.third] == -1) {
                    queue.add(next)
                    visit[next.first][next.second][next.third] = nextDist
                }

                // 벽 뚫기
                if (next.third != 1 && board[next.first][next.second] == '1' && visit[next.first][next.second][1] == -1) {
                    queue.add(next.copy(third = 1))
                    visit[next.first][next.second][1] = nextDist
                }
            }
        }
        return -1
    }

    bufferedWriter.append(bfsTry().toString()).flush()
    bufferedWriter.close()
    bufferedReader.close()
}