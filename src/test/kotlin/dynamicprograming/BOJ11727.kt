package dynamicprograming

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ11727Test {

    @Test
    fun testApplication() {
        val example = BOJIOReader().example("BOJ11727.txt", 3)
        BOJ11727().calc(example.inputStream, example.outputStream)
        assertEquals(example.getOutput(), example.getResult())
    }

}