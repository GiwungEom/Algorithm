package simulation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.abs


/**
 * 문제
 * 혜윤이는 최근에 다양한 대회를 참여하면서 노트북에 붙일 수 있는 스티커들을 많이 받았다. 스티커는 아래와 같이 사각 모눈종이 위에 인쇄되어 있으며, 스티커의 각 칸은 상하좌우로 모두 연결되어 있다. 또한 모눈종이의 크기는 스티커의 크기에 꼭 맞아서, 상하좌우에 스티커가 포함되지 않는 불필요한 행이나 열이 존재하지 않는다.
 *
 * 아래는 올바른 모눈종이의 예시이다. 주황색 칸은 스티커가 붙은 칸을, 하얀색 칸은 스티커가 붙지 않은 칸을 나타낸다.
 *
 *
 *
 * 반면 아래는 올바르지 않은 모눈종이의 예시이다. 첫 번째는 윗쪽에 불필요한 행이 있고, 두 번째는 왼쪽에 불필요한 열이 있다. 그리고 세 번째는 스티커의 각 칸이 상하좌우로 모두 연결되어 있지 않다.
 *
 *
 *
 * 혜윤이는 자신의 노트북에 이 스티커들을 붙이기로 했다. 혜윤이의 노트북은 마침 직사각형 모양이고, 스티커가 인쇄된 모눈종이와 같은 간격으로 격자가 그려져 있다. 혜윤이는 스티커들을 먼저 받았던 것부터 차례대로 격자에 맞춰서 붙이려고 한다.
 *
 * 혜윤이가 스티커를 붙이는 방법은 다음과 같다.
 *
 * 스티커를 회전시키지 않고 모눈종이에서 떼어낸다.
 * 다른 스티커와 겹치거나 노트북을 벗어나지 않으면서 스티커를 붙일 수 있는 위치를 찾는다. 혜윤이는 노트북의 위쪽부터 스티커를 채워 나가려고 해서, 스티커를 붙일 수 있는 위치가 여러 곳 있다면 가장 위쪽의 위치를 선택한다. 가장 위쪽에 해당하는 위치도 여러 곳이 있다면 그중에서 가장 왼쪽의 위치를 선택한다.
 * 선택한 위치에 스티커를 붙인다. 만약 스티커를 붙일 수 있는 위치가 전혀 없어서 스티커를 붙이지 못했다면, 스티커를 시계 방향으로 90도 회전한 뒤 2번 과정을 반복한다.
 * 위의 과정을 네 번 반복해서 스티커를 0도, 90도, 180도, 270도 회전시켜 봤음에도 스티커를 붙이지 못했다면 해당 스티커를 붙이지 않고 버린다.
 * 아래의 예시를 통해 스티커를 붙이는 과정을 이해해보자. 노트북은 세로 5칸, 가로 4칸 크기이고, 혜윤이가 가지고 있는 스티커들은 아래와 같다. 왼쪽에서 오른쪽 순으로 스티커를 붙일 것이다.
 *
 *
 *
 * 첫 번째 스티커는 회전 없이 온전히 붙일 수 있는 공간이 아래와 같이 6곳이 있다.
 *
 *
 *
 * 이 중에서 가장 위쪽의 위치, 가능한 가장 위쪽의 위치가 여러 개이면 그 중에서 가장 왼쪽의 위치는 첫 번째이다. 스티커를 붙인 후 노트북의 모양은 아래와 같다.
 *
 *
 *
 * 두 번째 스티커는 회전 없이 온전히 붙일 수 있는 공간이 없다. 그러나 시계 방향으로 90도 회전한 후에는 붙일 수 있는 공간이 1개 생긴다. 해당 공간에 스티커를 붙인 후 노트북의 모양은 아래와 같다.
 *
 *
 *
 * 세 번째 스티커는 스티커를 시계방향으로 0, 90, 180, 270도 회전시킨 모양에 대해 전부 확인을 해도 스티커를 붙일 수 있는 공간이 없다. 그러므로 해당 스티커를 붙이지 않고 버린다.
 *
 * 네 번째 스티커는 스티커를 시계방향으로 0, 90, 180도 회전 시킨 모양에 대해 온전히 붙일 수 있는 공간이 없다. 그러나 시계 방향으로 270도 회전한 후에는 공간이 1개 생긴다. 스티커를 붙인 후 노트북의 모양은 아래와 같다. 최종적으로 노트북의 18칸이 스티커로 채워졌다.
 *
 *
 *
 * 혜윤이는 스티커를 다 붙인 후의 노트북의 모습이 궁금해졌다. 노트북의 크기와 스티커들이 주어졌을 때 스티커들을 차례대로 붙이고 난 후 노트북에서 몇 개의 칸이 채워졌는지 구해보자.
 *
 * 입력
 * 첫째 줄에 노트북의 세로와 가로 길이를 나타내는 N(1 ≤ N ≤ 40)과 M(1 ≤ M ≤ 40), 그리고 스티커의 개수 K(1 ≤ K ≤ 100)이 한 칸의 빈칸을 사이에 두고 주어진다.
 *
 * 그 다음 줄부터는 K개의 스티커들에 대한 정보가 주어진다. 각 스티커는 아래와 같은 형식으로 주어진다.
 *
 * 먼저 i번째 스티커가 인쇄된 모눈종이의 행의 개수와 열의 개수를 나타내는 Ri(1 ≤ Ri ≤ 10)와 Ci(1 ≤ Ci ≤ 10)가 한 칸의 빈칸을 사이에 두고 주어진다.
 *
 * 다음 Ri개의 줄에는 각 줄마다 모눈종이의 각 행을 나타내는 Ci개의 정수가 한 개의 빈칸을 사이에 두고 주어진다. 각 칸에 들어가는 값은 0, 1이다. 0은 스티커가 붙지 않은 칸을, 1은 스티커가 붙은 칸을 의미한다.
 *
 * 문제에서 설명한 것과 같이 스티커는 모두 올바른 모눈종이에 인쇄되어 있다. 구체적으로 스티커의 각 칸은 상하좌우로 모두 연결되어 있고, 모눈종이의 크기는 스티커의 크기에 꼭 맞아서 상하좌우에 스티커에 전혀 포함되지 않는 불필요한 행이나 열이 존재하지 않는다.
 *
 * 출력
 * 첫째 줄에 주어진 스티커들을 차례대로 붙였을 때 노트북에서 스티커가 붙은 칸의 수를 출력한다.
 *
 * 예제 입력 1
 * 5 4 4
 * 3 3
 * 1 0 1
 * 1 1 1
 * 1 0 1
 * 2 5
 * 1 1 1 1 1
 * 0 0 0 1 0
 * 2 3
 * 1 1 1
 * 1 0 1
 * 3 3
 * 1 0 0
 * 1 1 1
 * 1 0 0
 * 예제 출력 1
 * 18
 * 예제 입력 2
 * 1 3 3
 * 2 3
 * 1 0 0
 * 1 1 1
 * 1 1
 * 1
 * 3 1
 * 1
 * 1
 * 1
 * 예제 출력 2
 * 1
 * 예제 입력 3
 * 2 3 3
 * 2 3
 * 1 1 1
 * 1 0 0
 * 2 1
 * 1
 * 1
 * 2 2
 * 1 0
 * 1 1
 * 예제 출력 3
 * 6
 * 예제 입력 4
 * 4 5 4
 * 3 3
 * 1 0 1
 * 1 1 1
 * 0 1 0
 * 2 4
 * 1 1 1 1
 * 0 1 0 1
 * 1 4
 * 1 1 1 1
 * 4 2
 * 1 0
 * 1 1
 * 0 1
 * 0 1
 * 예제 출력 4
 * 17
 * 예제 입력 5
 * 2 2 3
 * 3 1
 * 1
 * 1
 * 1
 * 2 3
 * 1 0 1
 * 1 1 1
 * 2 4
 * 1 0 1 1
 * 1 1 1 0
 * 예제 출력 5
 * 0
 * 예제 입력 6
 * 6 7 5
 * 4 6
 * 1 0 0 1 0 1
 * 1 1 0 1 0 1
 * 1 1 1 1 1 1
 * 0 0 0 1 0 0
 * 4 3
 * 0 1 0
 * 1 1 1
 * 0 1 1
 * 1 1 0
 * 3 6
 * 1 1 1 1 1 1
 * 0 0 1 0 0 0
 * 0 0 1 0 0 0
 * 6 6
 * 0 0 1 1 0 0
 * 1 1 1 1 0 1
 * 0 0 1 1 1 1
 * 0 0 1 1 1 1
 * 1 1 1 0 1 1
 * 0 1 0 0 1 0
 * 4 4
 * 1 1 1 1
 * 0 0 0 1
 * 0 0 1 1
 * 0 0 0 1
 * 예제 출력 6
 * 30
 * 예제 입력 7
 * 6 8 3
 * 4 5
 * 0 0 1 1 1
 * 1 1 1 0 1
 * 0 0 1 0 1
 * 0 0 1 0 0
 * 5 4
 * 0 0 1 0
 * 1 1 1 1
 * 1 1 0 1
 * 1 1 0 0
 * 1 1 0 0
 * 5 6
 * 0 0 1 1 1 1
 * 1 1 1 1 0 0
 * 1 1 1 1 1 0
 * 0 1 0 1 0 0
 * 0 1 0 1 0 0
 * 예제 출력 7
 * 22
 * 예제 입력 8
 * 8 6 6
 * 3 5
 * 0 1 0 0 0
 * 1 1 1 1 1
 * 0 1 0 0 1
 * 6 3
 * 0 0 1
 * 0 0 1
 * 0 0 1
 * 1 1 1
 * 1 0 1
 * 1 1 1
 * 6 3
 * 1 1 0
 * 1 0 0
 * 1 1 1
 * 1 0 1
 * 1 0 0
 * 1 0 0
 * 6 6
 * 0 0 0 0 1 0
 * 0 0 1 0 1 0
 * 0 0 1 0 1 0
 * 0 1 1 1 1 0
 * 0 1 1 0 1 1
 * 1 1 1 0 0 0
 * 4 5
 * 0 0 0 0 1
 * 1 0 0 1 1
 * 1 1 1 1 0
 * 1 1 0 1 0
 * 4 3
 * 1 1 0
 * 1 0 0
 * 1 1 1
 * 1 1 1
 * 예제 출력 8
 * 29
 */

fun main() {

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    var tokenizer = StringTokenizer(bufferedReader.readLine())
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()
    val k = tokenizer.nextToken().toInt()

    val noteBook = Array(size = n) { BooleanArray(size = m) }
    val stickers = ArrayList<Array<BooleanArray>>()
    var result = 0

    repeat(k) {
        tokenizer = StringTokenizer(bufferedReader.readLine())
        val x = tokenizer.nextToken().toInt()
        val y = tokenizer.nextToken().toInt()
        val sticker = Array(size = x) { BooleanArray(size = y) }
        repeat(x) { row ->
            tokenizer = StringTokenizer(bufferedReader.readLine())
            repeat(y) { col ->
                sticker[row][col] = tokenizer.nextToken().toInt() == 1
            }
        }

        stickers.add(sticker)
    }

    fun changeAngle(sticker: Array<BooleanArray>, angle: Int): Array<BooleanArray> {
        val result: Array<BooleanArray>
        val x = sticker.size
        val y = sticker[0].size
        if (angle == 0) {
            result = sticker

        } else if (angle == 90) {
            result = Array(size = y) { BooleanArray(size = x) }
            for (i in 0 until x) {
                for (j in 0 until y) {
                    result[j][x - 1 - i] = sticker[i][j]
                }
            }
        } else if (angle == 180) {
            result = Array(size = x) { BooleanArray(size = y) }
            for (i in 0 until x) {
                for (j in 0 until y) {
                    result[x - 1 - i][y - 1 - j] = sticker[i][j]
                }
            }
        } else if (angle == 270) {
            result = Array(size = y) { BooleanArray(size = x) }
            for (i in 0 until x) {
                for (j in 0 until y) {
                    result[y - 1 - j][i] = sticker[i][j]
                }
            }
        } else {
            throw IllegalStateException()
        }

        return result
    }

    fun checkStickerAvailable(nStartPos: Pair<Int, Int>, sticker: Array<BooleanArray>): Boolean {
        // 스티커 시작 지점
        val sStartPos = sticker.startPos()

        fun checkNotebook(next: Pair<Int, Int>): Boolean {
            val diff = abs(next.first - sStartPos.first) to abs(next.second - sStartPos.second)
            val noteBookPos = nStartPos.first + diff.first to nStartPos.second + diff.second
            if (noteBookPos.first !in noteBook.indices || noteBookPos.second !in noteBook[0].indices) return false
            if (noteBook[noteBookPos.first][noteBookPos.second]) return false
            return true
        }

        val visit = Array(size = sticker.size) { BooleanArray(size = sticker[0].size) }
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        val dx = intArrayOf(1, 0, -1, 0)
        val dy = intArrayOf(0, 1, 0, -1)
        // bfs 탐색하며 visit 여부 체크

        queue.add(sStartPos)
        visit[sStartPos.first][sStartPos.second] = true
        if (!checkNotebook(sStartPos)) return false

        while (queue.isNotEmpty()) {
            val cur = queue.remove()
            for (i in 0..3) {
                val next = cur.first + dx[i] to cur.second + dy[i]
                if (next.first !in sticker.indices || next.second !in sticker[0].indices) continue
                if (!sticker[next.first][next.second] || visit[next.first][next.second]) continue
                queue.add(next)
                visit[next.first][next.second] = true

                // 노트북 좌표 넘어갈시 return false
                if (!checkNotebook(next)) return false
            }
        }
        return true
    }

    fun attachSticker(nStartPos: Pair<Int, Int>, sticker: Array<BooleanArray>) {

        val visit = Array(size = sticker.size) { BooleanArray(size = sticker[0].size) }
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        val dx = intArrayOf(1, 0, -1, 0)
        val dy = intArrayOf(0, 1, 0, -1)
        // bfs 탐색하며 visit 여부 체크

        val sStartPos = sticker.startPos()
        queue.add(sStartPos)
        visit[sStartPos.first][sStartPos.second] = true
        noteBook[nStartPos.first][nStartPos.second] = true

        while (queue.isNotEmpty()) {
            val cur = queue.remove()
            for (i in 0..3) {
                val next = cur.first + dx[i] to cur.second + dy[i]
                if (next.first !in sticker.indices || next.second !in sticker[0].indices) continue
                if (!sticker[next.first][next.second] || visit[next.first][next.second]) continue
                queue.add(next)
                visit[next.first][next.second] = true

                val diff = next.first - sStartPos.first to next.second - sStartPos.second
                val noteBookPos = nStartPos.first + diff.first to nStartPos.second + diff.second
                noteBook[noteBookPos.first][noteBookPos.second] = true
            }
        }
    }

    infix fun Pair<Int, Int>.comp(other: Pair<Int, Int>): Pair<Int, Int> {
        if (this.first < other.first) {
            return this
        } else if (this.first == other.first) {
            if (this.second <= other.second) {
                return this
            } else {
                return other
            }
        } else {
            return other
        }
    }

    fun getStartPos(): Pair<Int, Int> {
        // 가장 좌측 상단의 값 찾기
        var startPos = n to m

        for (i in 0 until n) {
            for (j in m - 1 downTo 0) {
                if (noteBook[i][j]) {
                    break
                } else {
                    startPos = startPos comp (i to j)
                }
            }
        }

        for (i in 0 until m) {
            for (j in n - 1 downTo 0) {
                if (noteBook[j][i]) {
                    break
                } else {
                    startPos = startPos comp (j to i)
                }
            }
        }
        return startPos
    }

    stickers.forEach { sticker ->

        // 좌표 구하기
        val pos = getStartPos()

        // 스티커 붙이기 실패시
        // 90 ~ 270 까지 회전하면서 붙인다
        // 붙이기 실패시 다음 스티커로 넘어간다.
        for (i in 0 until 360 step 90) {
            val rotatedSticker = changeAngle(sticker, i)
            if (checkStickerAvailable(pos, rotatedSticker)) {
                attachSticker(pos, rotatedSticker)
                break
            }
        }
    }

    repeat(n) { x ->
        repeat(m) { y ->
            if (noteBook[x][y]) result++
        }
    }

    bufferedWriter.append(result.toString()).flush()
    bufferedWriter.close()
    bufferedReader.close()
}

private fun Array<BooleanArray>.startPos(): Pair<Int, Int> {
    this.forEachIndexed { x, booleans ->
        booleans.forEachIndexed { y, _ ->
            if (this[x][y]) return x to y
        }
    }
    throw IllegalStateException()
}








































