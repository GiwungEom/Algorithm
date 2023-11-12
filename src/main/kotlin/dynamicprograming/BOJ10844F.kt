package dynamicprograming

import java.io.*

class BOJ10844F {
    fun calc(inputStream: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))

        val n = bufferedReader.readLine().toInt()
        val d = Array(size = n + 1) { LongArray(size = 3) }
        d[1][1] = 1
        d[1][2] = 1

        for (i in 2 .. n) {
            d[i][2] = d[i-1][2] * 2
            d[i][1] = if (i % 2 == 1) d[i-1][1] * 2 - 1 else d[i-1][1] * 2
        }

        bufferedWriter.use {
            it.append((d[n][2] * 8 + d[n][1] % 1000000000).toString()).flush()
        }
        bufferedReader.close()
    }
}

fun main() {
    BOJ10844F().calc(System.`in`, System.out)
}