package dynamicprograming

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ1149Test {

    @Test
    fun applicationTest() {
        val example = BOJIOReader().example("BOJ1149.txt", 1)
        BOJ1149().calc(example.inputStream, example.outputStream)
        assertEquals(example.getOutput(), example.getResult())
    }
}