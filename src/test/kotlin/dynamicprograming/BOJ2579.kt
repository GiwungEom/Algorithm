package dynamicprograming

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ2579Test {

    @Test
    fun applicationTest() {
        val example = BOJIOReader().example("BOJ2579.txt", 1)
        BOJ2579().calc(example.inputStream, example.outputStream)
        assertEquals(example.getOutput(), example.getResult())
    }

}