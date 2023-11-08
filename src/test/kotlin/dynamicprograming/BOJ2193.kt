package dynamicprograming

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ2193Test {

    @Test
    fun testApplication() {
        val example = BOJIOReader().example("BOJ2193.txt", 2)
        BOJ2193().calc(example.inputStream, example.outputStream)
        assertEquals(example.getOutput(), example.getResult())
    }
}