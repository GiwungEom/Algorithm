package dynamicprograming

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ14501Test {
    @Test
    fun testApplication() {
        val example = BOJIOReader().example("BOJ14501.txt", 1)
        BOJ14501().calc(example.inputStream, example.outputStream)
        assertEquals(expected = example.getOutput(), actual = example.getResult())
    }
}