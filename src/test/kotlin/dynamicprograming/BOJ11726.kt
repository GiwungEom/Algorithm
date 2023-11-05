package dynamicprograming

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ11726Test {

    @Test
    fun applicationTest() {
        val example = BOJIOReader().example("BOJ11726.txt", 2)
        BOJ11726().calc(example.inputStream, example.outputStream)
        assertEquals(example.getOutput(), example.getResult())
    }
}