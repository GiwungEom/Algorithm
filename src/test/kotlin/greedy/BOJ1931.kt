package greedy

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class BOJ1931Test {

    @Test
    fun testFindMinimumSchedule() {
        val expected = listOf(1 to 4, 5 to 7, 8 to 11, 12 to 14)
        val input = listOf(1 to 4, 3 to 5, 0 to 6, 5 to 7, 3 to 8, 5 to 9, 6 to 10, 8 to 11, 8 to 12, 2 to 13, 12 to 14)
        assertContentEquals(expected = expected, actual = BOJ1931().findNextSchedule(input))
    }

    @Test
    fun testApplication() {
        val example = BOJIOReader().example("BOJ1931.txt", 1)
        BOJ1931().calc(example.inputStream, example.outputStream)
        assertEquals(example.getOutput(), example.getResult())
    }
}