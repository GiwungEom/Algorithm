package recursion

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * 문제
 * 한수는 크기가 2N × 2N인 2차원 배열을 Z모양으로 탐색하려고 한다.
 * 예를 들어, 2×2배열을 왼쪽 위칸, 오른쪽 위칸, 왼쪽 아래칸, 오른쪽 아래칸 순서대로 방문하면 Z모양이다.
 *
 *
 *
 * N > 1인 경우, 배열을 크기가 2N-1 × 2N-1로 4등분 한 후에 재귀적으로 순서대로 방문한다.
 *
 * 다음 예는 22 × 22 크기의 배열을 방문한 순서이다.
 *
 *
 *
 * N이 주어졌을 때, r행 c열을 몇 번째로 방문하는지 출력하는 프로그램을 작성하시오.
 *
 * 다음은 N=3일 때의 예이다.
 *
 *
 *
 * 입력
 * 첫째 줄에 정수 N, r, c가 주어진다.
 *
 * 출력
 * r행 c열을 몇 번째로 방문했는지 출력한다.
 *
 * 제한
 * 1 ≤ N ≤ 15
 * 0 ≤ r, c < 2N
 * 예제 입력 1
 * 2 3 1
 * 예제 출력 1
 * 11
 * 예제 입력 2
 * 3 7 7
 * 예제 출력 2
 * 63
 * 예제 입력 3
 * 1 0 0
 * 예제 출력 3
 * 0
 * 예제 입력 4
 * 4 7 7
 * 예제 출력 4
 * 63
 * 예제 입력 5
 * 10 511 511
 * 예제 출력 5
 * 262143
 * 예제 입력 6
 * 10 512 512
 * 예제 출력 6
 * 786432
 */

// 재귀 형태로써 문제 답은 만들어 지나
// 시간 복잡도에 의한 시간초과가 일어나서 실패함
// 모든 배열을 접근하면서 푸는 형태가 아닌
// 작업 단위가 끝났을시에 해당하는 결과를 저장해놓고
// 작업단위 수 만큼의 결과는 미리 계산하고 (시간 단축 효과)
// 나머지 작업에서만 배열 요소에 접근하며 진행 하면
// 시간 복잡도 더 낮아짐
fun main() {

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val tokenizer = StringTokenizer(bufferedReader.readLine())
    val n = tokenizer.nextToken().toInt()
    val target = tokenizer.nextToken().toInt() to tokenizer.nextToken().toInt()

    val dx = intArrayOf(0, 0, 1, 1)
    val dy = intArrayOf(0, 1, 0, 1)
    var count = 0

    fun search(n: Int, cur: Pair<Int, Int>) {
        if (n == 1) {
            for (i in 0..3) {
                count++
                val next = cur.first + dx[i] to cur.second + dy[i]
                if (next == target) {
                    bufferedWriter.append((count - 1).toString())
                }
            }
            return
        }

        val pos = 1.shl(n) / 2
        search(n - 1, cur.first + 0 to cur.second + 0)
        search(n - 1, cur.first + 0 to cur.second + pos)
        search(n - 1, cur.first + pos to cur.second + 0)
        search(n - 1, cur.first + pos to cur.second + pos)
    }

    search(n, 0 to 0)
    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()
}