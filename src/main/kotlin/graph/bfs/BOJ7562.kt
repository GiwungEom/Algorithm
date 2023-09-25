package graph.bfs

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * 문제
 * 체스판 위에 한 나이트가 놓여져 있다. 나이트가 한 번에 이동할 수 있는 칸은 아래 그림에 나와있다. 나이트가 이동하려고 하는 칸이 주어진다.
 * 나이트는 몇 번 움직이면 이 칸으로 이동할 수 있을까?
 *
 * 입력
 * 입력의 첫째 줄에는 테스트 케이스의 개수가 주어진다.
 *
 * 각 테스트 케이스는 세 줄로 이루어져 있다. 첫째 줄에는 체스판의 한 변의 길이 l(4 ≤ l ≤ 300)이 주어진다. 체스판의 크기는 l × l이다.
 * 체스판의 각 칸은 두 수의 쌍 {0, ..., l-1} × {0, ..., l-1}로 나타낼 수 있다. 둘째 줄과 셋째 줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.
 *
 * 출력
 * 각 테스트 케이스마다 나이트가 최소 몇 번만에 이동할 수 있는지 출력한다.
 *
 * 예제 입력 1
 * 3
 * 8
 * 0 0
 * 7 0
 * 100
 * 0 0
 * 30 50
 * 10
 * 1 1
 * 1 1
 * 예제 출력 1
 * 5
 * 28
 * 0
 */

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val input = bufferedReader.readLine()
    var tokenizer = StringTokenizer(input)
    val t = tokenizer.nextToken().toInt()
    // 좌상단 지점을 기준으로 시계방향으로 좌표 탐색
    val dx = intArrayOf(-1, -2, -2, -1, 1, 2, 2, 1)
    val dy = intArrayOf(-2, -1, 1, 2, 2, 1, -1, -2)

    repeat(t) {
        val boundary = bufferedReader.readLine().toInt()
        val startPosTokenizer = StringTokenizer(bufferedReader.readLine())
        val startPos = startPosTokenizer.nextToken().toInt() to startPosTokenizer.nextToken().toInt()
        val targetPosTokenizer = StringTokenizer(bufferedReader.readLine())
        val targetPos = targetPosTokenizer.nextToken().toInt() to targetPosTokenizer.nextToken().toInt()
        val visit = Array(size = boundary) { Array<Int>(size = boundary) { -1 } }

        val queue: Queue<Pair<Int, Int>> = LinkedList()
        queue.add(startPos)
        visit[startPos.first][startPos.second] = 0

        val bfsSearch = fun (): Int {
            while (queue.isNotEmpty()) {
                val cur = queue.remove()

                for (i in 0..7) {
                    val next = cur.first + dx[i] to cur.second + dy[i]

                    if (next.first < 0 || next.first >= boundary || next.second < 0 || next.second >= boundary) continue
                    if (visit[next.first][next.second] != -1) continue
                    if (targetPos == next) return visit[cur.first][cur.second] + 1

                    visit[next.first][next.second] = visit[cur.first][cur.second] + 1
                    queue.add(next)
                }
            }
            return 0
        }

        bufferedWriter.append(bfsSearch().toString()).appendLine()
    }
    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()
}












