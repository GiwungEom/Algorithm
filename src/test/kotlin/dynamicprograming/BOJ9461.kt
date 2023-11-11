package dynamicprograming

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ9461Test {
    @Test
    fun applicationTest() {
        val example = BOJIOReader().example("BOJ9461.txt", 1)
        BOJ9461().calc(example.inputStream, example.outputStream)
        assertEquals(expected = example.getOutput(), actual = example.getResult())
    }
}