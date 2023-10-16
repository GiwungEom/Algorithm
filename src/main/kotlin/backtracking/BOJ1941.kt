package backtracking

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * 문제
 * 총 25명의 여학생들로 이루어진 여학생반은 5×5의 정사각형 격자 형태로 자리가 배치되었고,
 * 얼마 지나지 않아 이다솜과 임도연이라는 두 학생이 두각을 나타내며 다른 학생들을 휘어잡기 시작했다.
 * 곧 모든 여학생이 ‘이다솜파’와 ‘임도연파’의 두 파로 갈라지게 되었으며, 얼마 지나지 않아 ‘임도연파’가 세력을 확장시키며 ‘이다솜파’를 위협하기 시작했다.
 *
 * 위기의식을 느낀 ‘이다솜파’의 학생들은 과감히 현재의 체제를 포기하고, ‘소문난 칠공주’를 결성하는 것이 유일한 생존 수단임을 깨달았다.
 * ‘소문난 칠공주’는 다음과 같은 규칙을 만족해야 한다.
 *
 * 이름이 이름인 만큼, 7명의 여학생들로 구성되어야 한다.
 * 강한 결속력을 위해, 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야 한다.
 * 화합과 번영을 위해, 반드시 ‘이다솜파’의 학생들로만 구성될 필요는 없다.
 * 그러나 생존을 위해, ‘이다솜파’가 반드시 우위를 점해야 한다. 따라서 7명의 학생 중 ‘이다솜파’의 학생이 적어도 4명 이상은 반드시 포함되어 있어야 한다.
 * 여학생반의 자리 배치도가 주어졌을 때, ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 'S'(이다‘솜’파의 학생을 나타냄) 또는 'Y'(임도‘연’파의 학생을 나타냄)을 값으로 갖는 5*5 행렬이 공백 없이 첫째 줄부터 다섯 줄에 걸쳐 주어진다.
 *
 * 출력
 * 첫째 줄에 ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수를 출력한다.
 *
 * 예제 입력 1
 * YYYYY
 * SYSYS
 * YYYYY
 * YSYYS
 * YYYYY
 * 예제 출력 1
 * 2
 */

fun main() {

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val board = Array(size = 5) { CharArray(size = 5) }

    repeat(5) { x ->
        val line = bufferedReader.readLine()

        repeat(5) { y ->
            board[x][y] = line.toCharArray()[y]
        }
    }

    val crew = IntArray(size = 7)
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    var result = 0

    fun checkCrew() {
        val start = crew[0] / 5 to crew[0] % 5
        val visit = BooleanArray(size = 7)
        queue.add(start)
        visit[0] = true
        var count = 1

        while (queue.isNotEmpty()) {
            val cur = queue.remove()

            for (i in 0..3) {
                val next = cur.first + dx[i] to cur.second + dy[i]
                val crewPosition = (if (next.first == 0) 0 else next.first * 5) + next.second
                if (next.first !in 0 until 5 || next.second !in 0 until 5) continue
                val crewNumber = crew.indexOf(crewPosition)
                if (crewNumber == -1 || visit[crewNumber]) continue
                queue.add(next)
                visit[crewNumber] = true
                count++
            }
        }

        if (count == 7) {
            result++
        }
    }

    fun makeCrew(cIndex: Int, startPosition: Int, yCount: Int) {
        if (yCount > 3) {
            return
        }

        if (cIndex == 7) {
            checkCrew()
            return
        }

        for (i in startPosition until 25) {
            crew[cIndex] = i
            makeCrew(cIndex + 1, i + 1, if (board[i / 5][i % 5] == 'Y') yCount + 1 else yCount)
        }
    }

    makeCrew(0, 0, 0)

    bufferedWriter.append(result.toString())
    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()
}