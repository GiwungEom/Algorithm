package graph.bfs

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


/**
 * 문제
 * 상근이는 빈 공간과 벽으로 이루어진 건물에 갇혀있다. 건물의 일부에는 불이 났고, 상근이는 출구를 향해 뛰고 있다.
 *
 * 매 초마다, 불은 동서남북 방향으로 인접한 빈 공간으로 퍼져나간다. 벽에는 불이 붙지 않는다. 상근이는 동서남북 인접한 칸으로 이동할 수 있으며, 1초가 걸린다.
 * 상근이는 벽을 통과할 수 없고, 불이 옮겨진 칸 또는 이제 불이 붙으려는 칸으로 이동할 수 없다. 상근이가 있는 칸에 불이 옮겨옴과 동시에 다른 칸으로 이동할 수 있다.
 *
 * 빌딩의 지도가 주어졌을 때, 얼마나 빨리 빌딩을 탈출할 수 있는지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 개수가 주어진다. 테스트 케이스는 최대 100개이다.
 *
 * 각 테스트 케이스의 첫째 줄에는 빌딩 지도의 너비와 높이 w와 h가 주어진다. (1 ≤ w,h ≤ 1000)
 *
 * 다음 h개 줄에는 w개의 문자, 빌딩의 지도가 주어진다.
 *
 * '.': 빈 공간
 * '#': 벽
 * '@': 상근이의 시작 위치
 * '*': 불
 * 각 지도에 @의 개수는 하나이다.
 *
 * 출력
 * 각 테스트 케이스마다 빌딩을 탈출하는데 가장 빠른 시간을 출력한다. 빌딩을 탈출할 수 없는 경우에는 "IMPOSSIBLE"을 출력한다.
 *
 * 예제 입력 1
 * 5
 * 4 3
 * ####
 * #*@.
 * ####
 * 7 6
 * ###.###
 * #*#.#*#
 * #.....#
 * #.....#
 * #..@..#
 * #######
 * 7 4
 * ###.###
 * #....*#
 * #@....#
 * .######
 * 5 5
 * .....
 * .***.
 * .*@*.
 * .***.
 * .....
 * 3 3
 * ###
 * #@#
 * ###
 * 예제 출력 1
 * 2
 * 5
 * IMPOSSIBLE
 * IMPOSSIBLE
 * IMPOSSIBLE
 */

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val n = bufferedReader.readLine().toInt()

    repeat(n) {
        val tokenizer = StringTokenizer(bufferedReader.readLine())
        val w = tokenizer.nextToken().toInt()
        val h = tokenizer.nextToken().toInt()
        val fireQ: Queue<Pair<Int,Int>> = LinkedList()
        val fireVisit = Array(size = h) { IntArray(size = w) { 0 } }
        val personQ: Queue<Pair<Int,Int>> = LinkedList()
        val personVisit = Array(size = h) { IntArray(size = w) { 0 } }
        val dx = intArrayOf(1, 0, -1, 0)
        val dy = intArrayOf(0, 1, 0, -1)

        val map = Array(size = h) { CharArray(size = w) }
        repeat(h) { height ->
            bufferedReader.readLine().forEachIndexed { index, c ->
                map[height][index] = c
                if (c == '*') {
                    fireQ.add(height to index)
                    fireVisit[height][index] = 1
                }
                if (c == '@') {
                    personQ.add(height to index)
                    personVisit[height][index] = 1
                }
            }
        }

        while (fireQ.isNotEmpty()) {
            val cur = fireQ.remove()

            for (i in 0..3) {
                val next = cur.first + dx[i] to cur.second + dy[i]

                if (next.first < 0 || next.first >= h || next.second < 0 || next.second >= w) continue
                if (map[next.first][next.second] == '#' || fireVisit[next.first][next.second] != 0) continue

//                fireVisit[next.first][next.second] = if (map[next.first][next.second] == '@') {
//                    fireVisit[cur.first][cur.second]
//                } else {
//                    fireVisit[cur.first][cur.second] + 1
//                }

                fireVisit[next.first][next.second] = fireVisit[cur.first][cur.second] + 1
                fireQ.add(next)
            }
        }

        val personEscape = fun (): String {
            while (personQ.isNotEmpty()) {
                val cur = personQ.remove()

                for (i in 0..3) {
                    val next = cur.first + dx[i] to cur.second + dy[i]

                    // 탈출 조건
                    if (next.first < 0 || next.first >= h || next.second < 0 || next.second >= w) {
                        return (personVisit[cur.first][cur.second]).toString()
                    }

                    if (map[next.first][next.second] == '#' || personVisit[next.first][next.second] != 0) continue
                    if (fireVisit[next.first][next.second] != 0 &&
                        fireVisit[next.first][next.second] <= personVisit[cur.first][cur.second] + 1) continue

                    personVisit[next.first][next.second] = personVisit[cur.first][cur.second] + 1
                    personQ.add(next)
                }
            }
            return "IMPOSSIBLE"
        }

        bufferedWriter.append(personEscape()).appendLine()
    }
    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()
}