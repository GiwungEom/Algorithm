package greedy

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ1026Test {
    @Test
    fun testApplication() {
        val example = BOJIOReader().example("BOJ1026.txt", 3)
        BOJ1026().calc(example.inputStream, example.outputStream)
        assertEquals(expected = example.getOutput(), example.getResult())
    }
}