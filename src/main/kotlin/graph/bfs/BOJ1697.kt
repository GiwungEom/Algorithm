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
 * 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
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
 * 4
 */

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val tokenizer = StringTokenizer(bufferedReader.readLine())
    val n = tokenizer.nextToken().toInt()
    val k = tokenizer.nextToken().toInt()
    val queue: Queue<Int> = LinkedList()
    val visit = IntArray(200000) { -1 }
    val dx = intArrayOf(-1, 1)

    queue.add(n)
    visit[n] = 0

    fun search(): Int {
        while (queue.isNotEmpty()) {
            val cur = queue.remove()
            for (i in 0..2) {
                val next = if (i < 2) {
                    cur + dx[i]
                } else {
                    cur * 2
                }

                if (next < 0 || next >= 200000) continue
                if (visit[next] != -1) continue
                if (next == k) return visit[cur] + 1

                visit[next] = visit[cur] + 1
                queue.add(next)
            }
        }
        return -1
    }
    val result = if (n == k) "0" else search().toString()
    bufferedWriter.apply {
        append(result)
        flush()
        close()
    }
    bufferedReader.close()
}