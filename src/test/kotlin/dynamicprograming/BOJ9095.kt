package dynamicprograming

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ9095Test {

    @Test
    fun applicationTest() {
        val example = BOJIOReader().example("BOJ9095.txt", 1)
        BOJ9095().calc(example.getInput(), example.outputStream)
        assertEquals(example.getOutput(), example.getResult().trimIndent())
    }
}