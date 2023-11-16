package greedy

import java.io.*
import java.util.*

class BOJ1026 {
    fun calc(inputStream: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))

        val n = bufferedReader.readLine().toInt()

        fun IntArray.init(): IntArray {
            val tokenizer = StringTokenizer(bufferedReader.readLine())
            repeat(n) {
                this[it] = tokenizer.nextToken().toInt()
            }
            return this
        }

        val a = IntArray(size = n).init().also { it.sort() }
        val b = IntArray(size = n).init().also { it.sortDescending() }
        var answer = 0
        for (i in 0 until n) {
            answer += a[i] * b[i]
        }
        bufferedWriter.use {
            it.append(answer.toString())
            it.flush()
        }
        bufferedReader.close()
    }
}

fun main() {
    BOJ1026().calc(System.`in`, System.out)
}