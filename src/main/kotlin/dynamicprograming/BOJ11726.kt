package dynamicprograming

import java.io.*

class BOJ11726 {
    fun calc(inputStream: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))

        val n = bufferedReader.readLine().toInt()
        val d = IntArray(size = 10005)

        d[1] = 1
        d[2] = 2

        for (i in 3..n) {
            d[i] = (d[i-1] + d[i-2]) % 10007
        }
        bufferedWriter.use {
            it.append(d[n].toString()).flush()
        }
        bufferedReader.close()
    }
}

fun main() {
    BOJ11726().calc(System.`in`, System.out)
}