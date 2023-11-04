package dynamicprograming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter

class BOJ9095 {
    fun calc(input: InputStream, output: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(input))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(output))

        val n = bufferedReader.readLine().toInt()
        val d = IntArray(12)
        d[1] = 1
        d[2] = 2
        d[3] = 4

        for (i in 4..11) {
            d[i] = d[i-1] + d[i-2] + d[i-3]
        }

        repeat(n) {
            bufferedWriter.append(d[bufferedReader.readLine().toInt()].toString()).appendLine()
        }

        bufferedWriter.flush()
        bufferedWriter.close()
        bufferedReader.close()
    }
}

fun main() {
    BOJ9095().calc(System.`in`, System.out)
}