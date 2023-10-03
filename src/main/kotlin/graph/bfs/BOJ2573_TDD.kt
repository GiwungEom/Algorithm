package graph.bfs

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max


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

    data class Input(
        val n: Int,
        val m: Int
    ) {
        var area: Array<IntArray> = Array(size = n) { IntArray(size = m) }
    }

    fun findStartPoint(area: Array<IntArray>): Pair<Int, Int> {
        repeat(area.size) { x ->
            repeat(area[x].size) { y ->
                if (area[x][y] > 0) return x to y
            }
        }
        throw NoSuchElementException()
    }

    fun checkBound(pos: Pair<Int, Int>, input: Input): Boolean =
        pos.first !in 0 until input.n && pos.second !in 0 until input.m

    fun countIceberg(area: Array<IntArray>): Int {
        var count = 0
        repeat(area.size) { x ->
            repeat(area[x].size) { y ->
                if (area[x][y] > 0) count++
            }
        }
        return count
    }

    // 연결 된 빙산 area 체크
    fun countIcebergBfs(input: Input): Int {
        val vis: Array<IntArray> = Array(size = input.n) { IntArray(size = input.m) }
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        val dx = intArrayOf(1, 0, -1, 0)
        val dy = intArrayOf(0, 1, 0, -1)

        var connectedIcebergCount = 0

        try {
            val startPos = findStartPoint(input.area)
            queue.add(startPos)
            vis[startPos.first][startPos.second] = 1
        } catch (_: NoSuchElementException) { }

        while (queue.isNotEmpty()) {
            val cur = queue.remove()
            ++connectedIcebergCount

            for (i in 0..3) {
                val next = cur.first + dx[i] to cur.second + dy[i]

                if (checkBound(next, input)) continue
                if (input.area[next.first][next.second] == 0 || vis[next.first][next.second] != 0) continue

                queue.add(next)
                vis[next.first][next.second] = 1
            }
        }

        return connectedIcebergCount
    }

    // 0 - 같음
    // 1 - 다름
    // 2 - 끝남
    fun checkIceburgState(input: Input): Int {
        val icebergCount = countIceberg(input.area)
        val connectedIcebergCount = countIcebergBfs(input)
        return if (icebergCount == 0) 2
        else if (icebergCount == connectedIcebergCount) 0
        else 1
    }

    fun countSea(input: Input): Array<IntArray> {
        val seaCount: Array<IntArray> = Array(size = input.n) { IntArray(size = input.m) }
        val dx = intArrayOf(1, 0, -1, 0)
        val dy = intArrayOf(0, 1, 0, -1)

        repeat(input.n) { x ->
            repeat(input.m) { y ->
                if (input.area[x][y] > 0) {
                    val cur = x to y
                    for (i in 0..3) {
                        val next = cur.first + dx[i] to cur.second + dy[i]
                        if (checkBound(next, input) || input.area[next.first][next.second] > 0) continue
                        seaCount[cur.first][cur.second]++
                    }
                }
            }
        }
        return seaCount
    }

    fun meltingIceburg(seaCount: Array<IntArray>, input: Input): Array<IntArray> {
        repeat(input.n) { x ->
            repeat(input.m) { y ->
                input.area[x][y] = max(0, input.area[x][y] - seaCount[x][y])
            }
        }
        return input.area
    }

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val fline = bufferedReader.readLine()
    var tokenizer = StringTokenizer(fline)
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

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

    var year = 0
    while (true) {
        year++

        input.area = meltingIceburg(countSea(input), input)

        val state = checkIceburgState(input)
        if (state == 1) break
        else if (state == 2) {
            year = 0
            break
        }
        else continue
    }
    bufferedWriter.append(year.toString()).appendLine()
    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()
}