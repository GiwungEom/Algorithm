package greedy

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ2217Test {
    @Test
    fun testApplication() {
        val example = BOJIOReader().example("BOJ2217.txt", 1)
        BOJ2217().calc(example.inputStream, example.outputStream)
        assertEquals(expected = example.getOutput(), actual = example.getResult())
    }
}