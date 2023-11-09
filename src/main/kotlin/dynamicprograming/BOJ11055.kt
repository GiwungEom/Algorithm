package dynamicprograming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.max

class BOJ11055 {
    fun calc(inputStream: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))
        val n = bufferedReader.readLine().toInt()
        val tokenizer = StringTokenizer(bufferedReader.readLine())
        val a = IntArray(size = n)
        val preSum = IntArray(size = a.size)
        repeat(n) {
            a[it] = tokenizer.nextToken().toInt()
            preSum[it] = a[it]
        }
        for (i in a.indices) {
            for (j in 0 until i) {
                if (a[j] < a[i]) {
                    preSum[i] = max(preSum[i], preSum[j] + a[i])
                }
            }
        }
        bufferedWriter.use {
            it.append(preSum.max().toString())
            it.flush()
        }
        bufferedReader.close()
    }
}

fun main() {
    BOJ11055().calc(System.`in`, System.out)
}