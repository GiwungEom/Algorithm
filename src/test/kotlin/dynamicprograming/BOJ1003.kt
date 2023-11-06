package dynamicprograming

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ1003Test {

    @Test
    fun testApplication() {
        val example = BOJIOReader().example("BOJ1003.txt", 2)
        BOJ1003().calc(example.inputStream, example.outputStream)
        assertEquals(example.getOutput(), example.getResult())
    }

}