package bfs

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.min
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * 여러 섬으로 이루어진 나라가 있다. 이 나라의 대통령은 섬을 잇는 다리를 만들겠다는 공약으로 인기몰이를 해 당선될 수 있었다. 하지만 막상 대통령에 취임하자, 다리를 놓는다는 것이 아깝다는 생각을 하게 되었다. 그래서 그는, 생색내는 식으로 한 섬과 다른 섬을 잇는 다리 하나만을 만들기로 하였고, 그 또한 다리를 가장 짧게 하여 돈을 아끼려 하였다.
 *
 * 이 나라는 N×N크기의 이차원 평면상에 존재한다. 이 나라는 여러 섬으로 이루어져 있으며, 섬이란 동서남북으로 육지가 붙어있는 덩어리를 말한다. 다음은 세 개의 섬으로 이루어진 나라의 지도이다.
 *
 *
 *
 * 위의 그림에서 색이 있는 부분이 육지이고, 색이 없는 부분이 바다이다. 이 바다에 가장 짧은 다리를 놓아 두 대륙을 연결하고자 한다. 가장 짧은 다리란, 다리가 격자에서 차지하는 칸의 수가 가장 작은 다리를 말한다. 다음 그림에서 두 대륙을 연결하는 다리를 볼 수 있다.
 *
 *
 *
 * 물론 위의 방법 외에도 다리를 놓는 방법이 여러 가지 있으나, 위의 경우가 놓는 다리의 길이가 3으로 가장 짧다(물론 길이가 3인 다른 다리를 놓을 수 있는 방법도 몇 가지 있다).
 *
 * 지도가 주어질 때, 가장 짧은 다리 하나를 놓아 두 대륙을 연결하는 방법을 찾으시오.
 *
 * 입력
 * 첫 줄에는 지도의 크기 N(100이하의 자연수)가 주어진다. 그 다음 N줄에는 N개의 숫자가 빈칸을 사이에 두고 주어지며, 0은 바다, 1은 육지를 나타낸다. 항상 두 개 이상의 섬이 있는 데이터만 입력으로 주어진다.
 *
 * 출력
 * 첫째 줄에 가장 짧은 다리의 길이를 출력한다.
 *
 * 예제 입력 1
 * 10
 * 1 1 1 0 0 0 0 1 1 1
 * 1 1 1 1 0 0 0 0 1 1
 * 1 0 1 1 0 0 0 0 1 1
 * 0 0 1 1 1 0 0 0 0 1
 * 0 0 0 1 0 0 0 0 0 1
 * 0 0 0 0 0 0 0 0 0 1
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 1 1 0 0 0 0
 * 0 0 0 0 1 1 1 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 예제 출력 1
 * 3
 *
 */


class TestBOJ2146_TDD {

    lateinit var input: Input
    lateinit var data: String
    @BeforeEach
    fun before() {
        data = """
            10
            1 1 1 0 0 0 0 1 1 1
            1 1 1 1 0 0 0 0 1 1
            1 0 1 1 0 0 0 0 1 1
            0 0 1 1 1 0 0 0 0 1
            0 0 0 1 0 0 0 0 0 1
            0 0 0 0 0 0 0 0 0 1
            0 0 0 0 0 0 0 0 0 0
            0 0 0 0 1 1 0 0 0 0
            0 0 0 0 1 1 1 0 0 0
            0 0 0 0 0 0 0 0 0 0
        """.trimIndent()
        input = readInput(data.split("\n"))
    }

    private fun readInput(data: List<String>): Input {
        return readNmInput(data).apply {
            area = inputToIntArray(n, m, data)
        }
    }

    private fun inputToIntArray(n: Int, m: Int, lines: List<String>): Array<IntArray> {
        val array = Array(size = n) { IntArray(size = m) }
        repeat(n) { x ->
            val tokenizer = StringTokenizer(lines[x + 1])
            repeat(m) { y ->
                array[x][y] = tokenizer.nextToken().toInt()
            }
        }
        return array
    }

    data class Input(
        val n: Int,
        val m: Int
    ) {
        var area: Array<IntArray> = Array(size = n) { IntArray(size = m) }
    }

    private fun readNmInput(data: List<String>): Input {
        return with(StringTokenizer(data[0])) {
            val n = nextToken().toInt()
            Input(n, n)
        }
    }

    @Test
    fun testInputNMData() {
        val input = readNmInput(data.split("\n"))
        assertEquals(10, input.n)
        assertEquals(10, input.m)
    }

    @Test
    fun testInputAreaData() {
        val lines = data.split("\n")
        val input = readNmInput(lines)
        input.area = inputToIntArray(input.n, input.m, lines)
        for (i in 1 until lines.size) {
            assertEquals(lines[i], input.area[i - 1].joinToString(" "))
        }
    }

    @Test
    fun testIslandAreaDivision() {
        val areaResult = """
            1 1 1 0 0 0 0 2 2 2
            1 1 1 1 0 0 0 0 2 2
            1 0 1 1 0 0 0 0 2 2
            0 0 1 1 1 0 0 0 0 2
            0 0 0 1 0 0 0 0 0 2
            0 0 0 0 0 0 0 0 0 2
            0 0 0 0 0 0 0 0 0 0
            0 0 0 0 3 3 0 0 0 0
            0 0 0 0 3 3 3 0 0 0
            0 0 0 0 0 0 0 0 0 0
        """.trimIndent()
        val expect = areaResult.split("\n")
        val actual = markAreaNumber(input)
        for (i in 0 until expect.size) {
            assertEquals(expect[i], actual[i].joinToString(" "))
        }
    }

    @Test
    fun testBuildBridge() {
        input.area = markAreaNumber(input)
        val bridgeLength = buildBridge(1, 4 to 4)
        assertEquals(3, bridgeLength)
    }

    @Test
    fun testGetStartPoints() {
        /**
         * val areaResult = """
         *              0 1 2 3 4 5 6 7 8 9
         *    0         1 1 1 0 0 0 0 2 2 2
         *    1         1 1 1 1 0 0 0 0 2 2
         *    2         1 0 1 1 0 0 0 0 2 2
         *    3         0 0 1 1 1 0 0 0 0 2
         *    4         0 0 0 1 0 0 0 0 0 2
         *    5         0 0 0 0 0 0 0 0 0 2
         *    6         0 0 0 0 0 0 0 0 0 0
         *    7         0 0 0 0 3 3 0 0 0 0
         *    8         0 0 0 0 3 3 3 0 0 0
         *    9         0 0 0 0 0 0 0 0 0 0
         *         """.trimIndent()
         */
        val expect: MutableMap<Int, Set<Pair<Int, Int>>> = HashMap()
        expect.put(1, setOf(3 to 0, 2 to 1, 3 to 1, 4 to 2, 5 to 3, 4 to 4, 3 to 5, 2 to 4, 1 to 4, 0 to 3))
        expect.put(2, setOf(0 to 6, 1 to 7, 2 to 7, 3 to 8, 4 to 8, 5 to 8, 6 to 9))
        expect.put(3, setOf(8 to 3, 7 to 3, 6 to 4, 6 to 5, 7 to 6, 8 to 7, 9 to 4, 9 to 5, 9 to 6))

        val actual = HashMap<Int, Set<Pair<Int, Int>>>()
        markAreaNumber(input, actual)
        repeat(3) {
            assertEquals(expect.getValue(it + 1).size, actual.getValue(it + 1).size)
            assertTrue(expect.getValue(it + 1).containsAll(actual.getValue(it + 1)))
        }
    }

    @Test
    fun testGetMinimumBridgeCount() {
        val startPoints = HashMap<Int, Set<Pair<Int, Int>>>()
        input.area = markAreaNumber(input, startPoints)
        val keys = startPoints.keys
        var minCount: Int = Int.MAX_VALUE
        for (key in keys) {
            startPoints.getValue(key).forEach {
                minCount = min(minCount, buildBridge(key, it))
            }
        }
        assertEquals(3, minCount)
    }

    fun checkBound(position: Pair<Int, Int>, input: Input) = position.first !in 0 until input.n || position.second !in 0 until input.m
    private fun buildBridge(startArea: Int, start: Pair<Int, Int>): Int {
        val vis: Array<IntArray> = Array(size = input.n) { IntArray(size = input.m) }
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        val dx = intArrayOf(1, 0, -1, 0)
        val dy = intArrayOf(0, 1, 0, -1)

        queue.add(start)
        vis[start.first][start.second] = 1

        while (queue.isNotEmpty()) {
            val cur = queue.remove()

            for (i in 0..3) {
                val next = cur.first + dx[i] to cur.second + dy[i]
                if (checkBound(next, input)) continue
                if (input.area[next.first][next.second] == startArea || vis[next.first][next.second] > 0) continue
                if (input.area[next.first][next.second] != 0) return vis[cur.first][cur.second]
                vis[next.first][next.second] = vis[cur.first][cur.second] + 1
                queue.add(next)
            }
        }
        return 0
    }

    private fun markAreaNumber(
        input: Input,
        startPointsMap: MutableMap<Int, Set<Pair<Int, Int>>> = HashMap()
    ): Array<IntArray> {
        val vis: Array<IntArray> = Array(size = input.n) { IntArray(size = input.m)}
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        val dx = intArrayOf(1, 0, -1, 0)
        val dy = intArrayOf(0, 1, 0, -1)
        var areaNumber = 0
        lateinit var startPoints: MutableSet<Pair<Int, Int>>

        repeat(input.n) { x ->
            repeat(input.m) { y ->
                if (input.area[x][y] > 0 && vis[x][y] == 0) {
                    queue.add(x to y)
                    vis[x][y] = 1
                    areaNumber++
                    input.area[x][y] = areaNumber
                    startPoints = HashSet()
                }

                while (queue.isNotEmpty()) {
                    val cur = queue.remove()
                    for (i in 0..3) {
                        val next = cur.first + dx[i] to cur.second + dy[i]
                        if (checkBound(next, input)) continue
                        if (input.area[next.first][next.second] == 0) {
                            startPoints.add(next)
                            continue
                        }
                        if (vis[next.first][next.second] == 1) continue

                        input.area[next.first][next.second] = areaNumber
                        vis[next.first][next.second] = 1
                        queue.add(next)
                    }
                }
                startPointsMap.put(areaNumber, startPoints)
            }
        }
        return input.area
    }
}































