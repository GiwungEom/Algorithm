package graph.bfs

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.min

fun main() {

    data class Input(
        val n: Int,
        val m: Int
    ) {
        var area: Array<IntArray> = Array(size = n) { IntArray(size = m) }
    }

    fun checkBound(position: Pair<Int, Int>, input: Input) = position.first !in 0 until input.n || position.second !in 0 until input.m
    fun buildBridge(startArea: Int, start: Pair<Int, Int>, input: Input): Int {
        val vis: Array<IntArray> = Array(size = input.n) { IntArray(size = input.m) }
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        val dx = intArrayOf(1, 0, -1, 0)
        val dy = intArrayOf(0, 1, 0, -1)

        queue.add(start)
        vis[start.first][start.second] = 1

        while (queue.isNotEmpty()) {
            val cur = queue.remove()

            for (i in 0..3) {
                val next = cur.first + dx[i] to cur.second + dy[i]
                if (checkBound(next, input)) continue
                if (input.area[next.first][next.second] == startArea || vis[next.first][next.second] > 0) continue
                if (input.area[next.first][next.second] != 0) return vis[cur.first][cur.second]
                vis[next.first][next.second] = vis[cur.first][cur.second] + 1
                queue.add(next)
            }
        }
        return 0
    }

    fun markAreaNumber(
        input: Input,
        startPointsMap: MutableMap<Int, Set<Pair<Int, Int>>> = HashMap()
    ): Array<IntArray> {
        val vis: Array<IntArray> = Array(size = input.n) { IntArray(size = input.m)}
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        val dx = intArrayOf(1, 0, -1, 0)
        val dy = intArrayOf(0, 1, 0, -1)
        var areaNumber = 0
        lateinit var startPoints: MutableSet<Pair<Int, Int>>

        repeat(input.n) { x ->
            repeat(input.m) { y ->
                if (input.area[x][y] > 0 && vis[x][y] == 0) {
                    queue.add(x to y)
                    vis[x][y] = 1
                    areaNumber++
                    input.area[x][y] = areaNumber
                    startPoints = HashSet()

                    while (queue.isNotEmpty()) {
                        val cur = queue.remove()
                        for (i in 0..3) {
                            val next = cur.first + dx[i] to cur.second + dy[i]
                            if (checkBound(next, input)) continue
                            if (input.area[next.first][next.second] == 0) {
                                startPoints.add(next)
                                continue
                            }
                            if (vis[next.first][next.second] == 1) continue

                            input.area[next.first][next.second] = areaNumber
                            vis[next.first][next.second] = 1
                            queue.add(next)
                        }
                    }
                    startPointsMap.put(areaNumber, startPoints)
                }
            }
        }
        return input.area
    }

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val fline = bufferedReader.readLine()
    var tokenizer = StringTokenizer(fline)
    val n = tokenizer.nextToken().toInt()
    val m = n

    val input = Input(n, m)
    repeat(n) { x ->
        val line = bufferedReader.readLine()
        tokenizer = StringTokenizer(line)
        repeat(m) { y ->
            tokenizer.nextToken().toInt().let {
                input.area[x][y] = it
            }
        }
    }

    val startPoints = HashMap<Int, Set<Pair<Int, Int>>>()
    input.area = markAreaNumber(input, startPoints)
    val keys = startPoints.keys
    var minCount: Int = Int.MAX_VALUE
    for (key in keys) {
        startPoints.getValue(key).forEach {
            minCount = min(minCount, buildBridge(key, it, input))
        }
    }

    bufferedWriter.append(minCount.toString()).flush()
    bufferedWriter.close()
    bufferedReader.close()
}