package graph.bfs

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max


fun main() {

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    val input = bufferedReader.readLine()
    var tokenizer = StringTokenizer(input)
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()
    var year = 0

    val area :Array<IntArray> = Array(size = 303) { IntArray(size = 303) }
    val vis :Array<IntArray> = Array(size = 303) { IntArray(size = 303) }

    fun check(i: Int, j: Int) = i in 0 until n && j in 0 until  m

    fun initVis() = repeat(n) { vis[it].fill(0) }

    // 1년의 시간 흐름을 진행
    fun melting() {
        val zero: Array<IntArray> = Array(size = 303) { IntArray(303) }

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (area[i][j] == 0) continue
                for (dir in 0..3) {
                    val nx = i + dx[dir]
                    val ny = j + dy[dir]
                    if (check(nx, ny) && area[nx][ny] == 0) zero[i][j]++
                }
            }
        }
        for (i in 0 until n) {
            for (j in 0 until m) {
                area[i][j] = max(0, area[i][j] - zero[i][j])
            }
        }
    }

    // 0 : 빙산이 다 녹음, 1 : 아직 한 덩이, 2 : 분리됨
    fun status(): Int {
        var x = -1; var y = -1
        var cnt1 = 0 // 빙산의 개수

        // area 에서 빙산 갯수 세기
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (area[i][j] > 0) {
                    x = i
                    y = j
                    cnt1++
                }
            }
        }
        if (cnt1 == 0) return 0

        var cnt2 = 0 // (x, y) 와 붙어있는 빙산의 수
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        vis[x][y] = 1 // 현재 위치 방문
        queue.add(x to y)
        while (queue.isNotEmpty()) {
            val cur = queue.remove()
            cnt2++
            for (i in 0 until 4) {
                val next = cur.first + dx[i] to cur.second + dy[i]
                if (!check(next.first, next.second) || vis[next.first][next.second] == 1 || area[next.first][next.second] <= 0) continue
                vis[next.first][next.second] = 1
                queue.add(next)
            }
        }
        return if (cnt1 == cnt2) 1 else 2
    }

    repeat(n) { x ->
        val line = bufferedReader.readLine()
        tokenizer = StringTokenizer(line)
        repeat(m) { y ->
            tokenizer.nextToken().toInt().let {
                area[x][y] = it
            }
        }
    }

    while (true) {
        year++      // 1년 추가
        melting()   // 빙산 녹이기
        initVis()   // 방문 배열 초기화

        val check = status()    // 빙산의 상태 확인
        if (check == 0) {
            year = 0
            break
        }
        else if (check == 1) continue
        else break
    }

    bufferedWriter.append(year.toString()).flush()
    bufferedWriter.close()
    bufferedReader.close()
}












