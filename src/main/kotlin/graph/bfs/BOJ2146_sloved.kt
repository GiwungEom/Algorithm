package graph.bfs

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Integer.min
import java.util.*

fun main() {

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val input = bufferedReader.readLine()
    var tokenizer = StringTokenizer(input)
    val n = tokenizer.nextToken().toInt()

    val board: Array<IntArray> = Array(size = n) { IntArray(size = n) }
    val vis: Array<IntArray> = Array(size = n) { IntArray(size = n) }
    val dist: Array<IntArray> = Array(size = n) { IntArray(size = n) { -1 } }

    val dx = intArrayOf(1, -1, 0, 0)
    val dy = intArrayOf(0, 0, -1, 1)
    var cnt = 0 // 섬 번호
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    var ans = Int.MAX_VALUE

    repeat(n) { x ->
        val line = bufferedReader.readLine()
        tokenizer = StringTokenizer(line)
        repeat(n) { y ->
            tokenizer.nextToken().toInt().let {
                board[x][y] = it
            }
        }
    }

    // 지도 섬 번호 적용
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (board[i][j] == 0 || vis[i][j] >= 1) continue
            cnt++
            vis[i][j] = 1
            board[i][j] = cnt
            queue.add(i to j)

            while (queue.isNotEmpty()) {
                val cur = queue.remove()
                for (dir in 0..3) {
                    val next = cur.first + dx[dir] to cur.second + dy[dir]
                    if (next.first < 0 || next.first >= n || next.second < 0 || next.second >= n) continue
                    if (vis[next.first][next.second] == 1 || board[next.first][next.second] == 0) continue
                    board[next.first][next.second] = cnt
                    vis[next.first][next.second] = 1
                    queue.add(next)
                }
            }
        }
    }

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (board[i][j] != 0) {
                queue.add(i to j)
                dist[i][j] = 0
                var escape = false

                while (queue.isNotEmpty() && !escape) {
                    val cur = queue.remove()

                    for (dir in 0..3) {
                        val next = cur.first + dx[dir] to cur.second + dy[dir]
                        if (next.first < 0 || next.first >= n || next.second < 0 || next.second >= n) continue
                        if (dist[next.first][next.second] >= 0 || board[i][j] == board[next.first][next.second]) continue
                        if (board[next.first][next.second] != 0 && board[i][j] != board[next.first][next.second]) {
                            ans = min(ans, dist[cur.first][cur.second])
                            while (queue.isNotEmpty()) {
                                queue.remove()
                            }
                            escape = true
                            break
                        }
                        dist[next.first][next.second] = dist[cur.first][cur.second] + 1
                        queue.add(next)
                    }
                }
                dist.forEachIndexed { x, ints ->
                    ints.forEachIndexed { y, _ ->
                        dist[x][y] = -1
                    }
                }
            }
        }
    }
    bufferedWriter.append(ans.toString()).flush()
    bufferedWriter.close()
    bufferedReader.close()
}
