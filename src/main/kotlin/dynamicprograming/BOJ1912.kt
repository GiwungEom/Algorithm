package dynamicprograming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.max

class BOJ1912 {
    fun calc(inputStream: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))

        val n = bufferedReader.readLine().toInt()
        val d = IntArray(size = 100005) { -1005 }
        val s = IntArray(size = 100005) { -1005 }

        val tokenizer = StringTokenizer(bufferedReader.readLine())
        repeat(n) {
            s[it + 1] = tokenizer.nextToken().toInt()
        }

        for (i in 1..n) {
            d[i] = max(s[i], d[i-1] + s[i])
        }

        bufferedWriter.use {
            it.append(d.max().toString()).flush()
        }
        bufferedReader.close()
    }
}

fun main() {
    BOJ1912().calc(System.`in`, System.out)
}