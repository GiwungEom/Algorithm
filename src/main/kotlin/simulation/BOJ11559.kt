package simulation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue


/**
 * 뿌요뿌요의 룰은 다음과 같다.
 *
 * 필드에 여러 가지 색깔의 뿌요를 놓는다. 뿌요는 중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어진다.
 *
 * 뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다. 이때 1연쇄가 시작된다.
 *
 * 뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면, 역시 중력의 영향을 받아 차례대로 아래로 떨어지게 된다.
 *
 * 아래로 떨어지고 나서 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터지게 되는데, 터진 후 뿌요들이 내려오고 다시 터짐을 반복할 때마다 1연쇄씩 늘어난다.
 *
 * 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.
 *
 * 남규는 최근 뿌요뿌요 게임에 푹 빠졌다. 이 게임은 1:1로 붙는 대전게임이라 잘 쌓는 것도 중요하지만, 상대방이 터뜨린다면 연쇄가 몇 번이 될지 바로 파악할 수 있는 능력도 필요하다. 하지만 아직 실력이 부족하여 남규는 자기 필드에만 신경 쓰기 바쁘다. 상대방의 필드가 주어졌을 때, 연쇄가 몇 번 연속으로 일어날지 계산하여 남규를 도와주자!
 *
 * 입력
 * 총 12개의 줄에 필드의 정보가 주어지며, 각 줄에는 6개의 문자가 있다.
 *
 * 이때 .은 빈공간이고 .이 아닌것은 각각의 색깔의 뿌요를 나타낸다.
 *
 * R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑이다.
 *
 * 입력으로 주어지는 필드는 뿌요들이 전부 아래로 떨어진 뒤의 상태이다. 즉, 뿌요 아래에 빈 칸이 있는 경우는 없다.
 *
 * 출력
 * 현재 주어진 상황에서 몇연쇄가 되는지 출력한다. 하나도 터지지 않는다면 0을 출력한다.
 *
 * 예제 입력 1
 * ......
 * ......
 * ......
 * ......
 * ......
 * ......
 * ......
 * ......
 * .Y....
 * .YG...
 * RRYG..
 * RRYGG.
 * 예제 출력 1
 * 3
 */

class BOJ11559 {
    fun breakPuyo(startPos: Pair<Int, Int>, board: Array<CharArray>): Boolean {
        val visit = Array(size = board.size) { BooleanArray(size = board[0].size) }
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        val connection: Queue<Pair<Int, Int>> = LinkedList()
        val dx = intArrayOf(1, 0, -1, 0)
        val dy = intArrayOf(0, 1, 0 , -1)
        val puyoType = board[startPos.first][startPos.second]

        queue.add(startPos)
        connection.add(startPos)
        visit[startPos.first][startPos.second] = true

        while (queue.isNotEmpty()) {
            val cur = queue.remove()

            for (i in 0..3) {
                val next = cur.first + dx[i] to cur.second + dy[i]
                if (next.first !in 0..board.lastIndex || next.second !in 0..board[0].lastIndex) continue
                if (visit[next.first][next.second] || board[next.first][next.second] != puyoType) continue

                visit[next.first][next.second] = true
                queue.add(next)
                connection.add(next)
            }
        }

        if (connection.size >= 4) {
            while (connection.isNotEmpty()) {
                val pos = connection.remove()
                board[pos.first][pos.second] = '.'
            }
            return true
        }

        return false
    }

    fun pullDownPuyo(board: Array<CharArray>): Boolean {
        var pullDown = false
        for (x in board.lastIndex - 1 downTo 0) {
            for (y in 0..board[0].lastIndex) {
                if (board[x][y] != '.') {
                    var next = x
                    while (true) {
                        next++
                        if (next > board.lastIndex || board[next][y] != '.') break
                        board[next][y] = board[next - 1][y]
                        board[next - 1][y] = '.'
                        pullDown = true
                    }
                }
            }
        }
        return pullDown
    }

    fun puyoPlay(input: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(input))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))

        val board = Array(size = 12) { CharArray(size = 6) }
        repeat(12) { row ->
            val line = bufferedReader.readLine().toCharArray()
            repeat(6) { col ->
                board[row][col] = line[col]
            }
        }

        var connectionCount = 0
        var breakPuyo = true

        while (breakPuyo) {
            breakPuyo = false
            for (x in 0 until board.size) {
                for (y in 0 until board[0].size) {
                    if (board[x][y] != '.') {
                        if (breakPuyo(startPos = x to y, board = board)) {
                            breakPuyo = true
                        }
                    }
                }
            }
            if (breakPuyo) {
                connectionCount++
                pullDownPuyo(board)
            }
        }

        bufferedWriter.append(connectionCount.toString()).flush()
        bufferedWriter.close()
    }
}

fun main() {
    val problem = BOJ11559()
    problem.puyoPlay(input = System.`in`, outputStream = System.out)
}
