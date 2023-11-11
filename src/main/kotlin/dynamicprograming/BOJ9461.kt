package dynamicprograming

import java.io.*

class BOJ9461 {
    fun calc(inputStream: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))

        val n = bufferedReader.readLine().toInt()

        val d = LongArray(size = 105)
        d[1] = 1
        d[2] = 1
        d[3] = 1
        d[4] = 2
        d[5] = 2

        for (i in 6..100) {
            d[i] = d[i-1] + d[i-5]
        }

        bufferedWriter.use { writer ->
            repeat(n) {
                writer.append(d[bufferedReader.readLine().toInt()].toString()).appendLine()
            }
            writer.flush()
        }
        bufferedReader.close()
    }
}

fun main() {
    BOJ9461().calc(System.`in`, System.out)
}