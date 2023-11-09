package dynamicprograming

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ11055Test {
    @Test
    fun applicationTest() {
        val example = BOJIOReader().example("BOJ11055.txt", 1)
        BOJ11055().calc(example.inputStream, example.outputStream)
        assertEquals(expected = example.getOutput(), example.getResult())
    }
}