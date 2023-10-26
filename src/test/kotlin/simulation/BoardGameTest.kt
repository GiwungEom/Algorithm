package simulation

import file.BOJIOReader
import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class BoardGameTest {

    @Test
    fun transferTo4DecimalTest() {
        val result = transfer4Decimal(1024 - 1)
        assertEquals(33333, result)
    }

    @Test
    fun moveSingleElementToWayTest() {
        val init = Array(size = 3) { IntArray(size = 3) }
        init[0][0] = 2
        val expect = Array(size = 3) { IntArray(size = 3) }
        expect[0][2] = 2

        for (i in 0..2) {
            assertContentEquals(expect[i], moveElementTo(init)[i])
        }
    }

    @Test
    fun moveTwoElementWithSameValueToWayTest() {
        val init = Array(size = 3) { IntArray(size = 3) }
        init[0][0] = 2
        init[0][1] = 2

        val expect = Array(size = 3) { IntArray(size = 3) }
        expect[0][2] = 4

        for (i in 0..2) {
            assertContentEquals(expect[i], moveElementTo(init)[i])
        }
    }

    @Test
    fun moveThreeElementWithSameValueToWayTest() {
        val init = Array(size = 3) { IntArray(size = 3) }
        init[0][0] = 2
        init[0][1] = 2
        init[0][2] = 2

        val expect = Array(size = 3) { IntArray(size = 3) }
        expect[0][1] = 2
        expect[0][2] = 4

        for (i in 0..2) {
            assertContentEquals(expect[i], moveElementTo(init)[i])
        }
    }

    @Test
    fun moveThreeElementWithDiffValueToWayTest() {
        val init = Array(size = 3) { IntArray(size = 3) }
        init[0][0] = 2
        init[0][1] = 4
        init[0][2] = 8

        val expect = Array(size = 3) { IntArray(size = 3) }
        expect[0][0] = 2
        expect[0][1] = 4
        expect[0][2] = 8

        for (i in 0..2) {
            assertContentEquals(expect[i], moveElementTo(init)[i])
        }
    }

    @Test
    fun moveThreeElementWithSameSumValueToWayTest() {
        val init = Array(size = 3) { IntArray(size = 3) }
        init[0][0] = 2
        init[0][1] = 2
        init[0][2] = 4

        val expect = Array(size = 3) { IntArray(size = 3) }
        expect[0][0] = 0
        expect[0][1] = 0
        expect[0][2] = 8

        moveElementTo(init)

        for (i in 0..2) {
            assertContentEquals(expect[i], moveElementTo(init)[i])
        }
    }

    @Test
    fun moveElementWithDiffWayTestCase1() {
        val init = Array(size = 4) { IntArray(size = 4) }
        init[0][0] = 4
        init[0][1] = 2
        init[3][0] = 2

        val expect = Array(size = 4) { IntArray(size = 4) }
        expect[0][3] = 8

        val ways = intArrayOf(1, 0, 1)

        val queue: Queue<Int> = LinkedList()
        queue.add(0)
        queue.add(1)
        queue.add(2)
        queue.add(3)

        var result: Array<IntArray> = Array(size = 4) { IntArray(size = 4) }
        for (way in ways) {
            while (queue.indexOf(1) != way) {
                rotate(init)
                queue.add(queue.remove())
            }
            result = moveElementTo(init)
        }

        for (i in 0..3) {
            assertContentEquals(expect[i], result[i])
        }
    }

    @Test
    fun moveElementsWithDiffWayTestCase2() {
        val init = Array(size = 4) { IntArray(size = 4) }
        init[0][0] = 2
        init[0][2] = 2
        init[0][3] = 8
        init[1][2] = 2
        init[1][3] = 2

        val expect = Array(size = 4) { IntArray(size = 4) }
        expect[0][0] = 4
        expect[0][1] = 8
        expect[1][0] = 4

        val ways = intArrayOf(3)

        val queue: Queue<Int> = LinkedList()
        queue.add(0)
        queue.add(1)
        queue.add(2)
        queue.add(3)

        var result: Array<IntArray> = Array(size = 4) { IntArray(size = 4) }
        for (way in ways) {
            while (queue.indexOf(1) != way) {
                rotate(init)
                queue.add(queue.remove())
            }
            result = moveElementTo(init)
        }

        rotate(result)
        rotate(result)

        for (i in 0..3) {
            assertContentEquals(expect[i], result[i])
        }
    }

    @Test
    fun moveElementsWithDiffWayTestCase3() {
        val init = Array(size = 4) { IntArray(size = 4) }
        init[0][0] = 2
        init[1][0] = 2
        init[2][0] = 2
        init[1][1] = 2

        val expect = Array(size = 4) { IntArray(size = 4) }
        expect[0][0] = 4
        expect[0][1] = 2
        expect[1][0] = 2

        val ways = intArrayOf(0)

        val queue: Queue<Int> = LinkedList()
        queue.add(0)
        queue.add(1)
        queue.add(2)
        queue.add(3)

        var result: Array<IntArray> = Array(size = 4) { IntArray(size = 4) }
        for (way in ways) {
            while (queue.indexOf(1) != way) {
                rotate(init)
                queue.add(queue.remove())
            }
            result = moveElementTo(init)
        }

        rotate(result)
        rotate(result)
        rotate(result)

        for (i in 0..3) {
            assertContentEquals(expect[i], result[i])
        }
    }

    @Test
    fun moveSingleElementWithDiffWay() {
        val init = Array(size = 3) { IntArray(size = 3) }
        init[0][0] = 2
        val expect = Array(size = 3) { IntArray(size = 3) }
        expect[2][0] = 2

        rotate(init)
        rotate(init)
        rotate(init)
        moveElementTo(init)
        rotate(init)

        for (i in 0..2) {
            assertContentEquals(expect[i], init[i])
        }
    }

    // 같은 값 더한 뒤 같은 값 테스트 (Done)
    // 같은 줄에 서로 다른 값 테스트 (Done)
    // 같은 값 더한 뒤 더한 값 테스트 (Done)

    @Test
    fun rotateElementTest() {
        val init = Array(size = 3) { IntArray(size = 3) }
        init[0][0] = 2
        val expect = Array(size = 3) { IntArray(size = 3) }
        expect[0][2] = 2

        val result = rotate(init)
        for (i in 0..2) {
            assertContentEquals(expect[i], result[i])
        }
    }

    @Test
    fun rotateQueueWithWayTest() {
        val way = 2

        val queue: Queue<Int> = LinkedList()
        queue.add(0)
        queue.add(1)
        queue.add(2)
        queue.add(3)

        var count = 0
        while (queue.indexOf(1) != way) {
            count++
            queue.add(queue.remove())
        }

        assertEquals(3, count)
    }


    @Test
    fun rotateBoardWithWayTest() {
        val init = Array(size = 3) { IntArray(size = 3) }
        init[0][2] = 2
        val expect = Array(size = 3) { IntArray(size = 3) }
        expect[0][0] = 2

        val way = 2

        val queue: Queue<Int> = LinkedList()
        queue.add(0)
        queue.add(1)
        queue.add(2)
        queue.add(3)

        var result: Array<IntArray> = Array(size = 3) { IntArray(size = 3) }
        while (queue.indexOf(1) != way) {
            result = rotate(init)
            queue.add(queue.remove())
        }

        for (i in 0..2) {
            assertContentEquals(expect[i], result[i])
        }
    }

    @Test
    fun applicationTest() {
        val example = BOJIOReader().example("BOJ12100.txt", 2)
        val bufferedReader = BufferedReader(InputStreamReader(example.getInput()))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(example.outputStream))

        val n = bufferedReader.readLine().toInt()
        val board = Array(size = n) { IntArray(size = n) }
        // 결과 저장
        var maxNumber = 0

        repeat(n) { row ->
            val tokenizer = StringTokenizer(bufferedReader.readLine())
            repeat(n) { col ->
                board[row][col] = tokenizer.nextToken().toInt()
            }
        }

        for (i in 0 until 1024) {
            val boardCopy = board.copyOf()
            val queue: Queue<Int> = LinkedList()
            queue.add(0)
            queue.add(1)
            queue.add(2)
            queue.add(3)

            var brute = i
            for (j in 0..4) {
                val way = brute % 4
                brute /= 4

                while (queue.indexOf(1) != way) {
                    rotate(boardCopy)
                    queue.add(queue.remove())
                }

                moveElementTo(boardCopy)
            }

            repeat(n) { x ->
                repeat(n) { y ->
                    maxNumber = max(boardCopy[x][y], maxNumber)
                }
            }
        }

        bufferedWriter.append(maxNumber.toString()).flush()
        bufferedWriter.close()
        bufferedReader.close()

        assertEquals(example.getOutput(), example.getResult())
    }

    private fun rotate(board: Array<IntArray>): Array<IntArray> {
        val r = board.size
        val c = board[0].size

        val temp = Array(size = board.size) { IntArray(size = board[0].size) }
        for (x in 0 until r) {
            for (y in 0 until c) {
                temp[x][y] = board[x][y]
            }
        }

        for (x in 0 until c) {
            for (y in 0 until r) {
                board[x][y] = temp[r - 1 - y][x]
            }
        }

        return board
    }

    private fun moveElementTo(board: Array<IntArray>): Array<IntArray> {
        val sumHistory = ArrayList<Pair<Int, Int>>()
        for (x in 0 until board[0].size) {
            for (y in board.lastIndex downTo 0) {
                if (board[x][y] != 0) {
                    for (i in y + 1 until board.size) {
                        if (sumHistory.contains(x to i)) break
                        if (board[x][i] != 0 && board[x][i] == board[x][i - 1]) {
                            board[x][i] = board[x][i] * 2
                            board[x][i - 1] = 0
                            break
                        } else if (board[x][i] == 0) {
                            board[x][i] = board[x][i - 1]
                            board[x][i - 1] = 0
                        } else {
                            break
                        }
                    }
                }
            }
        }
        return board
    }

    private fun transfer4Decimal(decimal: Int): Int {
        val stringBuffer = StringBuffer()
        var brute = decimal
        for (i in 0..4) {
            stringBuffer.append(brute % 4)
            brute /= 4
        }
        return stringBuffer.toString().toInt()
    }

}