package graph.bfs

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max


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
    val n = tokenizer.nextToken().toInt()
    val k = tokenizer.nextToken().toInt()
    val queue: Queue<Int> = LinkedList()
    val max = 100001
    val dist = IntArray(max + 2) { -1 }

    queue.add(n)
    dist[n] = 0

    fun getTime(type: Int): Int =  when (type) {
        0 -> 1
        1 -> 1
        2 -> 0
        else -> throw IllegalStateException()
    }

    // 기존 BFS 처럼 순차적으로 데이터를 찾으면 안됨
    // 순간이동이 0 초가 걸리기 때문에
    // 특정 순번 때 모든 순간이동을 Queue 에 넣어야 함.
    // 그렇지 않으면 순차적 순번이 꼬일 수 있음.
    // 따라서 아래와 같이 3개의 이동 케이스를 같은 순서로 구하면 안됨
    // 특정 next 의 모든 순간이동 케이스를 구하고 queue 에 넣고
    // 좌, 우측 케이스를 넣어야 함
    val search = fun (cur: Int): Int {
        for (type in 0..2) {
            val next = when (type) {
                0 -> cur + 1
                1 -> cur - 1
                2 -> if (cur == 0) -1 else cur * 2
                else -> throw IllegalStateException()
            }

            if (next < 0 || next >= max) continue
            if (dist[next] >= 0) continue
            queue.add(next)
            dist[next] = max(0, dist[cur] + getTime(type))
            if (next == k) {
                return 1
            }
        }
        return 0
    }

    while (queue.isNotEmpty()) {
        val cur = queue.remove()
        // 1 found, -1 not found
        val result = search(cur)
        println("cur $cur result $result dist[cur] ${dist[cur]}")
        if (result == 1) {
            bufferedWriter.append((dist[k]).toString()).flush()
            break
        }
    }

    bufferedWriter.close()
    bufferedReader.close()
}