package file

import org.junit.jupiter.api.Test
import java.io.*
import java.util.*
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class BOJIOReaderTest {

    private lateinit var example: Example

    @BeforeTest
    fun setUp() {
        example = BOJIOReader().example(
            name = "BOJ12100.txt",
            number = 1
        )
    }

    @Test
    fun testIoFileNotExistsTest() {
        var result = false
        try {
            BOJIOReader()
        } catch (e: FileNotFoundException) {
            result = true
        }
        assertEquals(false, result)
    }

    @Test
    fun testIoFileFoundTest() {
        val ioReader = BOJIOReader()
        val result = ioReader.exists("BOJ12100.txt")
        assertEquals(true, result)
    }

    @Test
    fun inputExampleFindTest() {
        assertEquals(true, example.numberExists())
    }

    @Test
    fun byteStreamEqualityTest() {
        val inputStream = """
            3
            2 2 2
            4 4 4
            8 8 8
        """.trimIndent().toByteArray().inputStream()

        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val result: Queue<String> = LinkedList<String>().apply {
            add("3")
            add("2 2 2")
            add("4 4 4")
            add("8 8 8")
        }
        while (true) {
            val expect: String = bufferedReader.readLine() ?: break
            assertEquals(expect, result.remove())
        }
    }

    @Test
    fun exampleInputTest() {
        val bufferedReader = BufferedReader(InputStreamReader(example.inputStream))
        val expect: Queue<String> = LinkedList<String>().apply {
            add("3")
            add("2 2 2")
            add("4 4 4")
            add("8 8 8")
        }
        while (true) {
            val result: String = bufferedReader.readLine() ?: break
            assertEquals(expect.remove(), result)
        }
    }

    @Test
    fun bufferedWriterWithOutputStreamTest() {
        val expect = "16"
        val bufferedWriter = BufferedWriter(OutputStreamWriter(example.outputStream))
        bufferedWriter.append(expect).flush()
        bufferedWriter.close()
        assertEquals(expect, example.getResult())
    }

    @Test
    fun exampleOutputTest() {
        val expect = "16"
        assertEquals(expect, example.getOutput())
    }

    @Test
    fun exampleOutputUsageTest() {
        val expect = "16"
        val bufferedWriter = BufferedWriter(OutputStreamWriter(example.outputStream))
        bufferedWriter.append(expect).flush()
        bufferedWriter.close()
        assertEquals(example.getResult(), example.getOutput())
    }
}