package greedy

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import kotlin.math.max

class BOJ2217 {
    fun calc(inputStream: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))

        val n = bufferedReader.readLine().toInt()
        val w = IntArray(size = n).apply {
            repeat(n) {
                this[it] = bufferedReader.readLine().toInt()
            }
            sort()
        }

        var answer = 0
        for (i in 1 ..  n) {
            answer = max(answer, w[n-i] * i)
        }
        bufferedWriter.use {
            it.append(answer.toString())
            it.flush()
        }
        bufferedReader.close()
    }
}

fun main() {
    BOJ2217().calc(System.`in`, System.out)
}