package dynamicprograming

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ10844Test {

    @Test
    fun testApplication() {
        val example = BOJIOReader().example("BOJ10844.txt", 3)
        BOJ10844Solved().calc(example.inputStream, example.outputStream)
        assertEquals(expected = example.getOutput(), example.getResult())
    }
}