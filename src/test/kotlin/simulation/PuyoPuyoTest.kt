package simulation

import file.BOJIOReader
import file.Example
import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.test.BeforeTest
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class PuyoPuyoTest {

    private lateinit var board: Array<CharArray>
    private lateinit var example: Example

    @BeforeTest
    fun setUp() {
        board = Array(size = 12) { CharArray(size = 6) }
        example = BOJIOReader().example("BOJ11559.txt", 1)
        val bufferedReader = BufferedReader(InputStreamReader(example.inputStream))
        repeat(12) { row ->
            val unit = bufferedReader.readLine().toCharArray()
            repeat(6) { column ->
                board[row][column] = unit[column]
            }
        }
    }

    @Test
    fun breakBubbleTest() {
        val problem = BOJ11559()
        problem.breakPuyo(startPos = 10 to 1, board)
        assertEquals(8, board.sumOf { it.count { pos -> pos != '.' } })
    }

    @Test
    fun pullDownBubbleTest() {
        val problem = BOJ11559()
        val expect = Array(size = 2) { CharArray(size = 6) }
        expect[0] = charArrayOf('.','Y','Y','G','.','.')
        expect[1] = charArrayOf('.','Y','Y','G','G','.')

        val preparation = Array(size = 4) { CharArray(size = 6) }
        preparation[0] = charArrayOf('.','Y','.','.','.','.')
        preparation[1] = charArrayOf('.','Y','G','.','.','.')
        preparation[2] = charArrayOf('.','.','Y','G','.','.')
        preparation[3] = charArrayOf('.','.','Y','G','G','.')

        problem.pullDownPuyo(preparation)
        val result = preparation.sliceArray(2..3)
        for (i in 0..1) {
            assertContentEquals(expected = expect[i], actual = result[i])
        }
    }

    @Test
    fun puyoTest() {
        // 해당 테스트 환경에서 실제 어플리케이션이 동작 되는 소스를 짰을 시
        // 제출해야 할 모델 소스 구현 시 중복이 될수 있다.
        // 따라서 테스트에서는 example 의 input, output 만 제공 하고
        // 결과 값만 outputStream 으로 받을 수 있게 만들면
        // 테스트에서 결과값은 byteArray outputStream 에서 확인,
        // 모델 소스에서의 결과 값은 system.out 의 outputStream 에서 확인 하면 됨으로써
        // 중복 소스 없이 모델 구현이 가능할 것으로 보인다.

        val problem = BOJ11559()
        problem.puyoPlay(example.inputStream, example.outputStream)
        assertEquals(expected = example.getOutput(), actual = example.getResult())
    }
}




















