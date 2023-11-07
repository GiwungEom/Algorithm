package dynamicprograming

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ1932Test {

    @Test
    fun testApplication() {
        val example = BOJIOReader().example("BOJ1932.txt", 1)
        BOJ1932().calc(example.inputStream, example.outputStream)
        assertEquals(example.getOutput(), example.getResult())
    }

    @Test
    fun testDiffArraySize() {
        var size = 1
        val result = Array(size = 2) { IntArray(size = size++) }
        assertEquals(1, result[0].size)
        assertEquals(2, result[1].size)
    }
}