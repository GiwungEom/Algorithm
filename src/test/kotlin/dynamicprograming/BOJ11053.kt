package dynamicprograming

import file.BOJIOReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BOJ11053Test {

    @Test
    fun applicationTest() {
        val example = BOJIOReader().example("BOJ11053.txt", 1)
        BOJ11053().calc(example.inputStream, example.outputStream)
        assertEquals(expected = example.getOutput(), actual = example.getResult())
    }
}