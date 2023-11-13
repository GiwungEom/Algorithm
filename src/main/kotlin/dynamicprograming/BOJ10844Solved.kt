package dynamicprograming

import java.io.*

class BOJ10844Solved {

    fun calc(inputStream: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))

        val n = bufferedReader.readLine().toInt()

        val d = Array(size = 105) { LongArray(size = 10) }
        for (i in 1..9) {
            d[1][i] = 1
        }

        for (i in 2 .. n) {
            for (j in 0..9) {
                if (j != 0) d[i][j] += d[i-1][j-1]
                if (j != 9) d[i][j] += d[i-1][j+1]
                d[i][j] = d[i][j] % 1000000000
            }
        }

        bufferedWriter.use {
            it.append((d[n].sum() % 1000000000).toString())
            it.flush()
        }
    }
}

fun main() {
    BOJ10844Solved().calc(System.`in`, System.out)
}