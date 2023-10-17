package simulation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * 문제
 * 스타트링크의 사무실은 1×1크기의 정사각형으로 나누어져 있는 N×M 크기의 직사각형으로 나타낼 수 있다.
 * 사무실에는 총 K개의 CCTV가 설치되어져 있는데, CCTV는 5가지 종류가 있다. 각 CCTV가 감시할 수 있는 방법은 다음과 같다.
 *
 *
 * 1번	2번	3번	4번	5번
 * 1번 CCTV는 한 쪽 방향만 감시할 수 있다. 2번과 3번은 두 방향을 감시할 수 있는데, 2번은 감시하는 방향이 서로 반대방향이어야 하고,
 * 3번은 직각 방향이어야 한다. 4번은 세 방향, 5번은 네 방향을 감시할 수 있다.
 *
 * CCTV는 감시할 수 있는 방향에 있는 칸 전체를 감시할 수 있다.
 * 사무실에는 벽이 있는데, CCTV는 벽을 통과할 수 없다. CCTV가 감시할 수 없는 영역은 사각지대라고 한다.
 *
 * CCTV는 회전시킬 수 있는데, 회전은 항상 90도 방향으로 해야 하며, 감시하려고 하는 방향이 가로 또는 세로 방향이어야 한다.
 *
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 1 0 6 0
 * 0 0 0 0 0 0
 * 지도에서 0은 빈 칸, 6은 벽, 1~5는 CCTV의 번호이다. 위의 예시에서 1번의 방향에 따라 감시할 수 있는 영역을 '#'로 나타내면 아래와 같다.
 *
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 1 # 6 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * # # 1 0 6 0
 * 0 0 0 0 0 0
 * 0 0 # 0 0 0
 * 0 0 # 0 0 0
 * 0 0 1 0 6 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 1 0 6 0
 * 0 0 # 0 0 0
 * →	←	↑	↓
 * CCTV는 벽을 통과할 수 없기 때문에, 1번이 → 방향을 감시하고 있을 때는 6의 오른쪽에 있는 칸을 감시할 수 없다.
 *
 * 0 0 0 0 0 0
 * 0 2 0 0 0 0
 * 0 0 0 0 6 0
 * 0 6 0 0 2 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 5
 * 위의 예시에서 감시할 수 있는 방향을 알아보면 아래와 같다.
 *
 * 0 0 0 0 0 #
 * # 2 # # # #
 * 0 0 0 0 6 #
 * 0 6 # # 2 #
 * 0 0 0 0 0 #
 * # # # # # 5
 * 0 0 0 0 0 #
 * # 2 # # # #
 * 0 0 0 0 6 #
 * 0 6 0 0 2 #
 * 0 0 0 0 # #
 * # # # # # 5
 * 0 # 0 0 0 #
 * 0 2 0 0 0 #
 * 0 # 0 0 6 #
 * 0 6 # # 2 #
 * 0 0 0 0 0 #
 * # # # # # 5
 * 0 # 0 0 0 #
 * 0 2 0 0 0 #
 * 0 # 0 0 6 #
 * 0 6 0 0 2 #
 * 0 0 0 0 # #
 * # # # # # 5
 * 왼쪽 상단 2: ↔, 오른쪽 하단 2: ↔	왼쪽 상단 2: ↔, 오른쪽 하단 2: ↕	왼쪽 상단 2: ↕, 오른쪽 하단 2: ↔	왼쪽 상단 2: ↕, 오른쪽 하단 2: ↕
 * CCTV는 CCTV를 통과할 수 있다. 아래 예시를 보자.
 *
 * 0 0 2 0 3
 * 0 6 0 0 0
 * 0 0 6 6 0
 * 0 0 0 0 0
 * 위와 같은 경우에 2의 방향이 ↕ 3의 방향이 ←와 ↓인 경우 감시받는 영역은 다음과 같다.
 *
 * # # 2 # 3
 * 0 6 # 0 #
 * 0 0 6 6 #
 * 0 0 0 0 #
 * 사무실의 크기와 상태, 그리고 CCTV의 정보가 주어졌을 때, CCTV의 방향을 적절히 정해서, 사각 지대의 최소 크기를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 사무실의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 8)
 *
 * 둘째 줄부터 N개의 줄에는 사무실 각 칸의 정보가 주어진다. 0은 빈 칸, 6은 벽, 1~5는 CCTV를 나타내고, 문제에서 설명한 CCTV의 종류이다.
 *
 * CCTV의 최대 개수는 8개를 넘지 않는다.
 *
 * 출력
 * 첫째 줄에 사각 지대의 최소 크기를 출력한다.
 *
 * 예제 입력 1
 * 4 6
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 1 0 6 0
 * 0 0 0 0 0 0
 * 예제 출력 1
 * 20
 * 예제 입력 2
 * 6 6
 * 0 0 0 0 0 0
 * 0 2 0 0 0 0
 * 0 0 0 0 6 0
 * 0 6 0 0 2 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 5
 * 예제 출력 2
 * 15
 * 예제 입력 3
 * 6 6
 * 1 0 0 0 0 0
 * 0 1 0 0 0 0
 * 0 0 1 0 0 0
 * 0 0 0 1 0 0
 * 0 0 0 0 1 0
 * 0 0 0 0 0 1
 * 예제 출력 3
 * 6
 * 예제 입력 4
 * 6 6
 * 1 0 0 0 0 0
 * 0 1 0 0 0 0
 * 0 0 1 5 0 0
 * 0 0 5 1 0 0
 * 0 0 0 0 1 0
 * 0 0 0 0 0 1
 * 예제 출력 4
 * 2
 * 예제 입력 5
 * 1 7
 * 0 1 2 3 4 5 6
 * 예제 출력 5
 * 0
 * 예제 입력 6
 * 3 7
 * 4 0 0 0 0 0 0
 * 0 0 0 2 0 0 0
 * 0 0 0 0 0 0 4
 * 예제 출력 6
 * 0
 */

fun main() {

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    var tokenizer = StringTokenizer(bufferedReader.readLine())
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

    val office = Array(size = n) { IntArray(size = m) }

    var minBlindCount = Int.MAX_VALUE

    val cctvs = ArrayList<Pair<Int, Int>>()

    repeat(n) { x ->
        tokenizer = StringTokenizer(bufferedReader.readLine())
        repeat(m) { y ->
            val info = tokenizer.nextToken().toInt()
            office[x][y] = info
            if (info in 1..5) cctvs.add(x to y)
        }
    }

    fun getWays(type: Int): Int = when (type) {
        1 -> 4
        2 -> 2
        3 -> 4
        4 -> 4
        5 -> 1
        else -> throw IllegalStateException()
    }

    // 7 - cctv vision area
    // 0 - cctv blind area
    fun getVision(power: Boolean): Int = if (power) 7 else 0

    fun moveCctvTop(cctvPos: Pair<Int, Int>, power: Boolean) {
        for (i in cctvPos.first - 1 downTo  0) {
            if (office[i][cctvPos.second] == 6) break
            else office[i][cctvPos.second] = getVision(power)
        }
    }

    fun moveCctvRight(cctvPos: Pair<Int, Int>, power: Boolean) {
        for (i in cctvPos.second + 1 until m) {
            if (office[cctvPos.first][i] == 6) break
            else office[cctvPos.first][i] = getVision(power)
        }
    }

    fun moveCctvLeft(cctvPos: Pair<Int, Int>, power: Boolean) {
        for (i in cctvPos.second - 1 downTo  0) {
            if (office[cctvPos.first][i] == 6) break
            else office[cctvPos.first][i] = getVision(power)
        }
    }

    fun moveCctvBottom(cctvPos: Pair<Int, Int>, power: Boolean) {
        for (i in cctvPos.first + 1 until n) {
            if (office[i][cctvPos.second] == 6) break
            else office[i][cctvPos.second] = getVision(power)
        }
    }

    fun moveCctv(
        cctvPos: Pair<Int, Int>,
        cctvType: Int,
        angle: Int,
        power: Boolean
    ) {
        // 벽에 걸리면 그 다음으로 갈수 없음
        // cctv 끼리는 통과 가능함
        // cctv 는 보이는 자리 이므로, power off 일때도 cctv 자리는 보이는 자리여야 함. (#)
        when (cctvType) {
            1 -> {
                if (angle == 0) moveCctvTop(cctvPos, power)
                else if (angle == 1) moveCctvRight(cctvPos, power)
                else if (angle == 2) moveCctvBottom(cctvPos, power)
                else moveCctvLeft(cctvPos, power)
            }
            2 -> {
                if (angle == 0) {
                    moveCctvTop(cctvPos, power)
                    moveCctvBottom(cctvPos, power)
                } else {
                    moveCctvRight(cctvPos, power)
                    moveCctvLeft(cctvPos, power)
                }
            }
            3 -> {
                if (angle == 0) {
                    moveCctvTop(cctvPos, power)
                    moveCctvRight(cctvPos, power)
                } else if (angle == 1) {
                    moveCctvRight(cctvPos, power)
                    moveCctvBottom(cctvPos, power)
                } else if (angle == 2) {
                    moveCctvBottom(cctvPos, power)
                    moveCctvLeft(cctvPos, power)
                } else {
                    moveCctvLeft(cctvPos, power)
                    moveCctvTop(cctvPos, power)
                }
            }
            4 -> {
                if (angle == 0) {
                    moveCctvTop(cctvPos, power)
                    moveCctvRight(cctvPos, power)
                    moveCctvLeft(cctvPos, power)
                } else if (angle == 1) {
                    moveCctvTop(cctvPos, power)
                    moveCctvRight(cctvPos, power)
                    moveCctvBottom(cctvPos, power)
                } else if (angle == 2) {
                    moveCctvRight(cctvPos, power)
                    moveCctvBottom(cctvPos, power)
                    moveCctvLeft(cctvPos, power)
                } else {
                    moveCctvBottom(cctvPos, power)
                    moveCctvLeft(cctvPos, power)
                    moveCctvTop(cctvPos, power)
                }
            }
            5 -> {
                moveCctvBottom(cctvPos, power)
                moveCctvLeft(cctvPos, power)
                moveCctvTop(cctvPos, power)
                moveCctvRight(cctvPos, power)
            }
            else -> throw IllegalStateException()
        }
    }

    fun checkBlindCount(): Int {
        var blindCount = 0
        repeat(n) { x ->
            repeat(m) { y ->
                if (office[x][y] == 0) {
                    blindCount++
                }
            }
        }
        return blindCount
    }

    // 각 하나의 CCTV 를 접근함
    fun func(cctvIndex: Int) {
        if (cctvIndex == cctvs.size) {
            val count = checkBlindCount()
            if (count < minBlindCount) {
                minBlindCount = count
            }
            return
        }

        // 백트래킹 상태를 갖고 있음
        val cctvPos = cctvs[cctvIndex]
        val cctvType = office[cctvPos.first][cctvPos.second]
        val way = getWays(cctvType)
        // 90 도 방향 돌림 총 4 방향
        for (angle in 0..way) {
            // 돌려서 상태를 변경 시킴
            moveCctv(cctvPos, cctvType, angle, true)
            func(cctvIndex + 1)
            // 변경 시킨 상태를 원복 시킴
            moveCctv(cctvPos, cctvType, angle, false)
        }
    }

    func(0)

    bufferedWriter.append(minBlindCount.toString()).flush()
    bufferedWriter.close()
    bufferedReader.close()
}






























