package graph.bfs

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

/**
 * 문제
 * 지구 온난화로 인하여 북극의 빙산이 녹고 있다. 빙산을 그림 1과 같이 2차원 배열에 표시한다고 하자.
 * 빙산의 각 부분별 높이 정보는 배열의 각 칸에 양의 정수로 저장된다. 빙산 이외의 바다에 해당되는 칸에는 0이 저장된다.
 * 그림 1에서 빈칸은 모두 0으로 채워져 있다고 생각한다.
 *
 *
 *  	2	4	5	3
 *  	3	 	2	5	2
 *  	7	6	2	4
 *
 * 그림 1. 행의 개수가 5이고 열의 개수가 7인 2차원 배열에 저장된 빙산의 높이 정보
 *
 * 빙산의 높이는 바닷물에 많이 접해있는 부분에서 더 빨리 줄어들기 때문에,
 * 배열에서 빙산의 각 부분에 해당되는 칸에 있는 높이는 일년마다 그 칸에 동서남북 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼 줄어든다.
 * 단, 각 칸에 저장된 높이는 0보다 더 줄어들지 않는다. 바닷물은 호수처럼 빙산에 둘러싸여 있을 수도 있다.
 * 따라서 그림 1의 빙산은 일년후에 그림 2와 같이 변형된다.
 *
 * 그림 3은 그림 1의 빙산이 2년 후에 변한 모습을 보여준다. 2차원 배열에서 동서남북 방향으로 붙어있는 칸들은 서로 연결되어 있다고 말한다.
 * 따라서 그림 2의 빙산은 한 덩어리이지만, 그림 3의 빙산은 세 덩어리로 분리되어 있다.
 *
 *
 *  	 	2	4	1
 *  	1	 	1	5
 *  	5	4	1	2
 *
 * 그림 2
 *
 *
 *  	 	 	3
 *  	 	 	 	4
 *  	3	2
 *
 * 그림 3
 *
 * 한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램을 작성하시오.
 * 그림 1의 빙산에 대해서는 2가 답이다. 만일 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 프로그램은 0을 출력한다.
 *
 * 입력
 * 첫 줄에는 이차원 배열의 행의 개수와 열의 개수를 나타내는 두 정수 N과 M이 한 개의 빈칸을 사이에 두고 주어진다.
 * N과 M은 3 이상 300 이하이다. 그 다음 N개의 줄에는 각 줄마다 배열의 각 행을 나타내는 M개의 정수가 한 개의 빈 칸을 사이에 두고 주어진다.
 * 각 칸에 들어가는 값은 0 이상 10 이하이다. 배열에서 빙산이 차지하는 칸의 개수, 즉, 1 이상의 정수가 들어가는 칸의 개수는 10,000 개 이하이다.
 * 배열의 첫 번째 행과 열, 마지막 행과 열에는 항상 0으로 채워진다.
 *
 * 출력
 * 첫 줄에 빙산이 분리되는 최초의 시간(년)을 출력한다. 만일 빙산이 다 녹을 때까지 분리되지 않으면 0을 출력한다.
 *
 * 예제 입력 1
 * 5 7
 * 0 0 0 0 0 0 0
 * 0 2 4 5 3 0 0
 * 0 3 0 2 5 2 0
 * 0 7 6 2 4 0 0
 * 0 0 0 0 0 0 0
 * 예제 출력 1
 * 2
 *
 * 예제 입력 2
 * 3 3
 * 0 1 1
 * 1 1 1
 * 1 1 0
 * 예제 출력 2
 * 2
 */

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val input = bufferedReader.readLine()
    var tokenizer = StringTokenizer(input)
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

    // 최대 10 회 라운드
    val board: Array<Array<IntArray>> = Array(size = 11) { Array(size = n) { IntArray(size = m) } }
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    var icebergCount = 0 // 빙산 갯수 (나뉘어 졌는지 판단에 사용)
    var nextIcebergCount = 0 // 빙산 갯수 (나뉘어 졌는지 판단에 사용)
    var period = 0

    repeat(n) { x ->
        val line = bufferedReader.readLine()
        tokenizer = StringTokenizer(line)
        repeat(m) { y ->
            tokenizer.nextToken().toInt().let {
                board[0][x][y] = it
                if (it != 0) {
                    icebergCount++
                }
            }
        }
    }

    fun findStart(round: Int): Pair<Int, Int> {
        repeat(n) { x ->
            repeat(m) { y ->
                if (board[round][x][y] >= 1) return x to y
            }
        }
        throw IllegalStateException()
    }

    for (round in 0..10) {
        val visit: Array<IntArray> = Array(size = n) { IntArray(size = m) { -1 } }

        if (icebergCount == 0) {
            break
        } else {
            val start = findStart(round)
            queue.add(start)
            visit[start.first][start.second] = round
        }

        var bfsCount = 0

        // 나뉘어 졌는지 판단 후에 빙산을 녹이려 했으나,
        // 동시에 이뤄질수 있을 것으로 보임.
        // BFS 진행 하면서, Board 의 데이터를 변경
        // 1 이상일 경우 남아있는 빙산 카운트
        // 다음 라운드에서 빙산 녹일 때 이전 빙산 카운트와 비교
        // 다음 라운드 빙산 카운트가, 이전 빙산 카운트보다 작을때
        // 빙산이 나뉘어 졌다고 판단 가능.
        // 시간 복잡도 2n -> n 으로 변경 가능
        while (queue.isNotEmpty()) {
            val cur = queue.remove()
            bfsCount++
            // 빙산 녹이기
            // 빙산이 다 녹지 않았으면 빙산 카운트

            // 다음 빙산 찾기
            var curData = board[round][cur.first][cur.second]
            var seaAround = false
            for (j in 0..3) {
                val next = cur.first + dx[j] to cur.second + dy[j]
                if (next.first < 0 || next.first >= n || next.second < 0 || next.second >= m) continue

                // 다음 회차 빙산 구하기
                if (board[round][next.first][next.second] == 0) {
                    if (board[round][cur.first][cur.second] > 0) {
                        board[round + 1][cur.first][cur.second] = --curData
                        seaAround = true
                    }
                    continue
                }

                if (board[round][next.first][next.second] < 0 || visit[next.first][next.second] == round) continue

                visit[next.first][next.second] = round
                queue.add(next)
            }

            if (!seaAround) {
                board[round + 1][cur.first][cur.second] = board[round][cur.first][cur.second]
            }
            if (board[round + 1][cur.first][cur.second] > 0) nextIcebergCount++
        }

        // 분리 됨
        if (icebergCount != bfsCount) {
            break
        } else {
            if (nextIcebergCount == 0) { // 라운드 내에 빙산이 남아 있지 않은 경우
                period = 0
                break
            } else {
                icebergCount = nextIcebergCount
            }
        }

        period++
    }

    if (period == 11) {
        period = 0
    }

    bufferedWriter.append(period.toString()).flush()
    bufferedWriter.close()
    bufferedReader.close()
}