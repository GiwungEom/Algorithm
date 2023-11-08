package dynamicprograming

import java.io.*

class BOJ2193 {
    fun calc(inputStream: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))

        val n = bufferedReader.readLine().toInt()
        val d = Array(size = 100) { LongArray(size = 2) }
        d[1][0] = 0
        d[1][1] = 1
        d[2][0] = 1
        d[2][1] = 0

        for (i in 3..n) {
            d[i][0] = d[i-1][0] + d[i-1][1]
            d[i][1] = d[i-1][0]
        }

        bufferedWriter.use {
            it.append((d[n][0] + d[n][1]).toString()).flush()
        }
        bufferedReader.close()
    }
}

fun main() {
    BOJ2193().calc(System.`in`, System.out)
}
