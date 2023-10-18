package simulation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.min


fun main() {

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    var tokenizer = StringTokenizer(bufferedReader.readLine())
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

    val office1 = Array(size = n) { IntArray(size = m) }
    val office2 = Array(size = n) { IntArray(size = m) }

    val cctvs = ArrayList<Pair<Int, Int>>()
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    var officeBlind = 0

    repeat(n) { x ->
        tokenizer = StringTokenizer(bufferedReader.readLine())
        repeat(m) { y ->
            val info = tokenizer.nextToken().toInt()
            office1[x][y] = info

            if (info in 1..5) cctvs.add(x to y)
            if (info == 0) officeBlind++
        }
    }

    fun moveCctv(pos: Pair<Int, Int>, direction: Int) {
        var next: Pair<Int, Int> = pos
        val originDirection = direction % 4
        while (true) {
            next = next.first + dx[originDirection] to next.second + dy[originDirection]

            if (next.first < 0 || next.first >= n || next.second < 0 || next.second >= m) break
            if (office2[next.first][next.second] == 6) break
            if (office2[next.first][next.second] in 1..5) continue

            office2[next.first][next.second] = 7
        }
    }

    // cctv 방향을 나타내는 4진수
    for (i in 0 until 1.shl(2 * cctvs.size)) {
        // 초기화
        repeat(n) { x -> repeat(m) { y -> office2[x][y] = office1[x][y] } }

        var brute = i
        // cctv 방향에 따른 시야 계산. direction - 0 하단, 1 우측, 2 상단, 3 좌측
        for (j in 0 until cctvs.size) {
            val direction = brute % 4
            brute = if (brute == 0) 0 else brute / 4

            val pos = cctvs[j]
            val type = office1[pos.first][pos.second]
            when (type) {
                1 -> moveCctv(pos, direction)
                2 -> {
                    moveCctv(pos, direction)
                    moveCctv(pos, direction + 2)
                }
                3 -> {
                    moveCctv(pos, direction)
                    moveCctv(pos, direction + 1)
                }
                4 -> {
                    moveCctv(pos, direction)
                    moveCctv(pos, direction + 1)
                    moveCctv(pos, direction + 2)
                }
                5 -> {
                    moveCctv(pos, direction)
                    moveCctv(pos, direction + 1)
                    moveCctv(pos, direction + 2)
                    moveCctv(pos, direction + 3)
                }
                else -> throw IllegalStateException()
            }
        }

        var blindCount = 0
        // 사각지대 계산
        for (x in 0 until n) {
            for (y in 0 until m) {
                if (office2[x][y] == 0) {
                    blindCount++
                }
            }
        }
        officeBlind = min(officeBlind, blindCount)
    }
    bufferedWriter.append(officeBlind.toString()).flush()
    bufferedWriter.close()
    bufferedReader.close()
}










