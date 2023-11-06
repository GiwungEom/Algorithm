package dynamicprograming

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ12852Test {

    @Test
    fun testApplication() {
        val example = BOJIOReader().example("BOJ12852.txt", 2)
        BOJ12852().calc(example.inputStream, example.outputStream)
        assertEquals(example.getOutput(), example.getResult())
    }
}