package graph.bfs

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * 문제
 * 지훈이는 미로에서 일을 한다. 지훈이를 미로에서 탈출하도록 도와주자!
 *
 * 미로에서의 지훈이의 위치와 불이 붙은 위치를 감안해서 지훈이가 불에 타기전에 탈출할 수 있는지의 여부, 그리고 얼마나 빨리 탈출할 수 있는지를 결정해야한다.
 *
 * 지훈이와 불은 매 분마다 한칸씩 수평또는 수직으로(비스듬하게 이동하지 않는다) 이동한다.
 *
 * 불은 각 지점에서 네 방향으로 확산된다.
 *
 * 지훈이는 미로의 가장자리에 접한 공간에서 탈출할 수 있다.
 *
 * 지훈이와 불은 벽이 있는 공간은 통과하지 못한다.
 *
 * 입력
 * 입력의 첫째 줄에는 공백으로 구분된 두 정수 R과 C가 주어진다. 단, 1 ≤ R, C ≤ 1000 이다. R은 미로 행의 개수, C는 열의 개수이다.
 *
 * 다음 입력으로 R줄동안 각각의 미로 행이 주어진다.
 *
 * 각각의 문자들은 다음을 뜻한다.
 *
 * #: 벽
 * .: 지나갈 수 있는 공간
 * J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간)
 * F: 불이 난 공간
 * J는 입력에서 하나만 주어진다.
 *
 * 출력
 * 지훈이가 불이 도달하기 전에 미로를 탈출 할 수 없는 경우 IMPOSSIBLE 을 출력한다.
 *
 * 지훈이가 미로를 탈출할 수 있는 경우에는 가장 빠른 탈출시간을 출력한다.
 *
 * 예제 입력 1
 * 4 4
 * ####
 * #JF#
 * #..#
 * #..#
 * 예제 출력 1
 * 3
 */

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val input = bufferedReader.readLine()
    val tokenizer = StringTokenizer(input)
    val r = tokenizer.nextToken().toInt()
    val c = tokenizer.nextToken().toInt()

    val board = Array(size = r) { CharArray(size = c) }
    val queue1: Queue<Pair<Int, Int>> = LinkedList()
    val queue2: Queue<Pair<Int, Int>> = LinkedList()
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    val dist1 = Array(size = r) { IntArray(size = c) { -1 } }
    val dist2 = Array(size = r) { IntArray(size = c) { -1 } }

    repeat(r) { i ->
        val row = bufferedReader.readLine()
        repeat(row.length) { j ->
            val spot = row.elementAt(j)
            board[i][j] = spot
            if (spot == 'F') {
                queue1.add(i to j)
                dist1[i][j] = 0
            } else if (spot == 'J') {
                queue2.add(i to j)
                dist2[i][j] = 0
            }
        }
    }
    // fire bfs
    while (queue1.isNotEmpty()) {
        val cur = queue1.remove()

        for (i in 0..3) {
            val next = cur.first + dx[i] to cur.second + dy[i]

            if (next.first < 0 || next.first >= r || next.second < 0 || next.second >= c) continue
            if (dist1[next.first][next.second] >= 0 || board[next.first][next.second] == '#') continue

            dist1[next.first][next.second] = dist1[cur.first][cur.second] + 1
            queue1.add(next)
        }
    }

    fun humanBfs(): String {
        while (queue2.isNotEmpty()) {
            val cur = queue2.remove()

            for (i in 0..3) {
                val next = cur.first + dx[i] to cur.second + dy[i]

                if (next.first < 0 || next.first >= r || next.second < 0 || next.second >= c) {
                    return (dist2[cur.first][cur.second] + 1).toString()
                }
                if (dist2[next.first][next.second] >= 0 || board[next.first][next.second] == '#') continue
                if (dist1[next.first][next.second] != -1 && dist1[next.first][next.second] <= dist2[cur.first][cur.second] + 1) continue

                dist2[next.first][next.second] = dist2[cur.first][cur.second] + 1
                queue2.add(next)
            }
        }
        return "IMPOSSIBLE"
    }

    val result = humanBfs()

    bufferedWriter.apply {
        append(result)
        flush()
        close()
    }
    bufferedReader.close()
}