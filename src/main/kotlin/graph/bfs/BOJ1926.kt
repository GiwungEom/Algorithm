package graph.bfs

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * 문제
 * 어떤 큰 도화지에 그림이 그려져 있을 때, 그 그림의 개수와, 그 그림 중 넓이가 가장 넓은 것의 넓이를 출력하여라.
 * 단, 그림이라는 것은 1로 연결된 것을 한 그림이라고 정의하자. 가로나 세로로 연결된 것은 연결이 된 것이고 대각선으로 연결이 된 것은 떨어진 그림이다.
 * 그림의 넓이란 그림에 포함된 1의 개수이다.
 *
 * 입력
 * 첫째 줄에 도화지의 세로 크기 n(1 ≤ n ≤ 500)과 가로 크기 m(1 ≤ m ≤ 500)이 차례로 주어진다. 두 번째 줄부터 n+1 줄 까지 그림의 정보가 주어진다.
 * (단 그림의 정보는 0과 1이 공백을 두고 주어지며, 0은 색칠이 안된 부분, 1은 색칠이 된 부분을 의미한다)
 *
 * 출력
 * 첫째 줄에는 그림의 개수, 둘째 줄에는 그 중 가장 넓은 그림의 넓이를 출력하여라. 단, 그림이 하나도 없는 경우에는 가장 넓은 그림의 넓이는 0이다.
 *
 * 예제 입력 1
 * 6 5
 * 1 1 0 1 1
 * 0 1 1 0 0
 * 0 0 0 0 0
 * 1 0 1 1 1
 * 0 0 1 1 1
 * 0 0 1 1 1
 * 예제 출력 1
 * 4
 * 9
 */

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val input = bufferedReader.readLine()
    val tokenizer = StringTokenizer(input)
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

    val board = Array(size = n) { IntArray(size = m) }
    val visit = Array(size = n) { Array(size = m) { false } }
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    repeat(n) { i ->
        val drawing = bufferedReader.readLine()
        val dTokenizer = StringTokenizer(drawing)
        repeat(m) { j ->
            board[i][j] = dTokenizer.nextToken().toInt()
        }
    }

    var maxCount = 0
    var drawCount = 0
    repeat(n) { i ->
        repeat(m) { j ->
            if (board[i][j] == 1 && !visit[i][j]) {
                visit[i][j] = true
                queue.add(i to j)
                drawCount++

                var count = 0

                while (!queue.isEmpty()) {
                    val cur = queue.remove().also { count++ }
                    for (p in 0 until 4) {
                        val x = cur.first + dx[p]
                        val y = cur.second + dy[p]

                        if (x < 0 || x >= n || y < 0 || y >= m) continue
                        if (board[x][y] != 1 || visit[x][y]) continue

                        queue.add(x to y)
                        visit[x][y] = true
                    }
                }

                if (count > maxCount) {
                    maxCount = count
                }
            }
        }
    }
    bufferedWriter.append(drawCount.toString()).appendLine().append(maxCount.toString())
    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()
}