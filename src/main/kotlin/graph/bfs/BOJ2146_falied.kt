package graph.bfs

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.min

fun main() {

    data class Input(
        val n: Int,
        val m: Int
    ) {
        var area: Array<IntArray> = Array(size = n) { IntArray(size = m) }
    }

    fun checkBound(position: Pair<Int, Int>, input: Input) =
        position.first !in 0 until input.n || position.second !in 0 until input.m

    fun buildBridge(input: Input): Int {
        val dist: Array<IntArray> = Array(size = input.n) { IntArray(size = input.m) { -1 } }

        val queue: Queue<Pair<Int, Int>> = LinkedList()
        val dx = intArrayOf(1, 0, -1, 0)
        val dy = intArrayOf(0, 1, 0, -1)
        var ans = Int.MAX_VALUE

        for (i in 0 until input.n) {
            for (j in 0 until input.n) {
                if (input.area[i][j] != 0) {
                    queue.add(i to j)
                    dist[i][j] = 0
                }

                var found = false
                while (queue.isNotEmpty() && !found) {
                    val cur = queue.remove()

                    for (dir in 0..3) {
                        val next = cur.first + dx[dir] to cur.second + dy[dir]

                        if (checkBound(next, input)) continue
                        if (input.area[i][j] == input.area[next.first][next.second] || dist[next.first][next.second] >= 0) continue
                        if (input.area[next.first][next.second] != 0 && input.area[i][j] != input.area[next.first][next.second]) {
                            ans = min(ans, dist[cur.first][cur.second])
                            while (queue.isNotEmpty()) {
                                queue.remove()
                            }
                            found = true
                            break
                        }

                        queue.add(next)
                        dist[next.first][next.second] = dist[cur.first][cur.second] + 1
                    }
                }

                dist.forEachIndexed { x, ints ->
                    ints.forEachIndexed { y, _ ->
                        dist[x][y] = -1
                    }
                }
            }
        }
        return ans
    }

    fun markAreaNumber(
        input: Input
    ): Array<IntArray> {
        val vis: Array<IntArray> = Array(size = input.n) { IntArray(size = input.m) }
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        val dx = intArrayOf(1, 0, -1, 0)
        val dy = intArrayOf(0, 1, 0, -1)
        var areaNumber = 0

        repeat(input.n) { x ->
            repeat(input.m) { y ->
                if (input.area[x][y] > 0 && vis[x][y] == 0) {
                    queue.add(x to y)
                    vis[x][y] = 1
                    areaNumber++
                    input.area[x][y] = areaNumber
                }

                while (queue.isNotEmpty()) {
                    val cur = queue.remove()
                    for (i in 0..3) {
                        val next = cur.first + dx[i] to cur.second + dy[i]
                        if (checkBound(next, input)) continue
                        if (input.area[next.first][next.second] == 0) {
                            continue
                        }
                        if (vis[next.first][next.second] == 1) continue

                        input.area[next.first][next.second] = areaNumber
                        vis[next.first][next.second] = 1
                        queue.add(next)
                    }
                }
            }
        }
        return input.area
    }

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val fLine = bufferedReader.readLine()
    var tokenizer = StringTokenizer(fLine)
    val n = tokenizer.nextToken().toInt()
    val input = Input(n = n, m = n)

    repeat(n) { x ->
        val line = bufferedReader.readLine()
        tokenizer = StringTokenizer(line)
        repeat(n) { y ->
            tokenizer.nextToken().toInt().let {
                input.area[x][y] = it
            }
        }
    }

    input.area = markAreaNumber(input)
    bufferedWriter.append(buildBridge(input).toString()).flush()
    bufferedWriter.close()
    bufferedReader.close()
}