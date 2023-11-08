package file

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import kotlin.text.Charsets.UTF_8

class BOJIOReader(
    private val defaultPath: String = "./src/test/resources/input"
) {
    private val file: File = File(defaultPath)

    init {
        if (file.listFiles()?.isEmpty() != false) {
            throw FileNotFoundException("Must Provide Test Io File")
        }
    }

    fun exists(fileName: String): Boolean {
        return File("$defaultPath/$fileName").exists()
    }

    fun example(
        name: String,
        number: Int = -1
    ): Example {
        return Example(
            fileName = "$defaultPath/$name",
            number = number
        )
    }
}

class Example(
    fileName: String,
    var number: Int
) {

    private val iToken: String
        get() = "#${number}I"
    private val oToken: String
        get() = "#${number}O"

    private val returnAscii = 10
    private val file: File

    val outputStream: ByteArrayOutputStream = ByteArrayOutputStream()

    init {
        file = File(fileName)
        if (!file.exists()) {
            throw FileNotFoundException("$fileName Not Found")
        }
    }

    // 문제 풀이 결과
    fun getResult(): String {
        return outputStream.toByteArray().toString(UTF_8).trimIndent()
    }

    fun numberExists(): Boolean {
        return file.bufferedReader().use {
            var line: String? = ""

            while (line != null) {
                line = it.readLine()
                if (line == "#${number}I") {
                    return@use true
                }
            }
            return@use false
        }
    }

    // 인풋 스트림
    val inputStream: InputStream
        get() {
            val offsetRange = file.inputStream().use {
                val buffer = ByteArray(size = 1024)
                var offset = 0
                var length = 0
                var sOffset = 0
                val eOffset: Int

                while (true) {
                    val data = it.read()
                    if (data == -1) { throw IllegalStateException("Not Found Example $number") }
                    buffer[offset++] = data.toByte()
                    length++
                    if (data == returnAscii) {
                        val line = String(buffer.copyOfRange(offset - length, offset - 1))
                        if (line == iToken) {
                            sOffset = offset
                        }
                        if (line == oToken) {
                            eOffset = offset - length
                            break
                        }

                        length = 0
                    }
                }
                return@use sOffset to eOffset
            }

            val byteArray = ByteArray(offsetRange.second - offsetRange.first)
            file.range(byteArray, offsetRange.first, offsetRange.second)
            return byteArray.inputStream()
        }
//    fun inputStream: InputStream {
//        val offsetRange = file.inputStream().use {
//            val buffer = ByteArray(size = 1024)
//            var offset = 0
//            var length = 0
//            var sOffset = 0
//            val eOffset: Int
//
//            while (true) {
//                val data = it.read()
//                if (data == -1) { throw IllegalStateException("Not Found Example $number") }
//                buffer[offset++] = data.toByte()
//                length++
//                if (data == returnAscii) {
//                    val line = String(buffer.copyOfRange(offset - length, offset - 1))
//                    if (line == iToken) {
//                        sOffset = offset
//                    }
//                    if (line == oToken) {
//                        eOffset = offset - length
//                        break
//                    }
//
//                    length = 0
//                }
//            }
//            return@use sOffset to eOffset
//        }
//
//        val byteArray = ByteArray(offsetRange.second - offsetRange.first)
//        file.range(byteArray, offsetRange.first, offsetRange.second)
//        return byteArray.inputStream()
//    }

    // 예제 결과
    fun getOutput(): String {
        val offsetRange = file.inputStream().use {
            val buffer = ByteArray(size = 1024)
            var offset = 0
            var length = 0
            var sOffset = 0
            val eOffset: Int

            while (true) {
                val data = it.read()
                if (data == -1) {
                    eOffset = offset
                    break
                }
                buffer[offset++] = data.toByte()
                length++
                if (data == returnAscii) {
                    val line = String(buffer.copyOfRange(offset - length, offset - 1))
                    if (line == oToken) {
                        sOffset = offset
                    }
                    if (line == "#${number+1}I") {
                        eOffset = offset - length - 1 // 1 : return count
                        break
                    }
                    length = 0
                }
            }
            return@use sOffset to eOffset
        }

        val byteArray = ByteArray(offsetRange.second - offsetRange.first)
        file.range(byteArray, offsetRange.first, offsetRange.second)
        return byteArray.toString(UTF_8)
    }

}

private fun File.range(byteArray: ByteArray, startOffset: Int, endOffset: Int) {
    inputStream().use {
        it.skip(startOffset.toLong())
        it.read(byteArray, 0, endOffset - startOffset)
    }
}
