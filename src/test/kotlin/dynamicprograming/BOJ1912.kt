package dynamicprograming

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ1912Test {

    @Test
    fun applicationTest() {
        val example = BOJIOReader().example("BOJ1912.txt", 3)
        BOJ1912().calc(example.inputStream, example.outputStream)
        assertEquals(example.getOutput(), example.getResult())
    }
}