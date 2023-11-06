package dynamicprograming

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ11659Test {

    @Test
    fun applicationTest() {
        val example = BOJIOReader().example("BOJ11659.txt", 1)
        BOJ11659().sum(example.inputStream, example.outputStream)
        assertEquals(example.getOutput(), example.getResult().trimIndent())
    }
}