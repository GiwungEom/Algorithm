package dynamicprograming

import java.io.*

class BOJ1003 {
    fun calc(inputStream: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))

        val d = Array(size = 41) { IntArray(size = 2) }
        d[0][0] = 1
        d[0][1] = 0
        d[1][0] = 0
        d[1][1] = 1

        for (i in 2..40) {
            d[i][0] = d[i - 1][0] + d[i - 2][0]
            d[i][1] = d[i - 1][1] + d[i - 2][1]
        }

        val t = bufferedReader.readLine().toInt()
        repeat(t) {
            val n = bufferedReader.readLine().toInt()
            bufferedWriter.append(d[n][0].toString()).append(" ").append(d[n][1].toString()).appendLine()
        }
        bufferedWriter.use {
            it.flush()
        }
        bufferedReader.close()
    }
}

fun main() {
    BOJ1003().calc(System.`in`, System.out)
}