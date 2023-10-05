package graph.bfs

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


/**
 * 문제
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다.
 * 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
 * 순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동하게 된다.
 *
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
 *
 * 출력
 * 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
 *
 * 예제 입력 1
 * 5 17
 * 예제 출력 1
 * 2
 * 힌트
 * 수빈이가 5-10-9-18-17 순으로 가면 2초만에 동생을 찾을 수 있다.
 */

fun main() {

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val line = bufferedReader.readLine()
    var tokenizer = StringTokenizer(line)
    val sis = tokenizer.nextToken().toInt()
    val bro = tokenizer.nextToken().toInt()
    val queue: Queue<Int> = LinkedList()
    val max = 100001
    val board = IntArray(max + 2)

    fun teleport(num: Int) {
        var tmp = num
        if (tmp == 0) return
        while (tmp < max && board[bro] == 0) {
            if (board[tmp] == 0) {
                board[tmp] = board[num]
                queue.add(tmp)
                if (tmp == bro) return
            }
            tmp *= 2
        }
    }

    board[sis] = 1
    queue.add(sis)
    teleport(sis)

    while (board[bro] == 0) {
        val v = queue.remove()
        val intArray = intArrayOf(v + 1, v - 1)
        for (nv in intArray) {
            if (nv < 0 || max <= nv) continue
            if (board[nv] != 0) continue
            board[nv] = board[v] + 1
            queue.add(nv)
            teleport(nv)
        }
    }

    bufferedWriter.append((board[bro] - 1).toString()).flush()
    bufferedWriter.close()
    bufferedReader.close()
}





