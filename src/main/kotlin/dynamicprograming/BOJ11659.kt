package dynamicprograming

import java.io.*
import java.util.*

class BOJ11659 {
    fun sum(inputStream: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))

        var tokenizer = StringTokenizer(bufferedReader.readLine())
        val n = tokenizer.nextToken().toInt()
        val m = tokenizer.nextToken().toInt()

        val a = IntArray(size = n + 1)
        val d = IntArray(size = n + 1)

        tokenizer = StringTokenizer(bufferedReader.readLine())
        repeat(n) {
            a[it + 1] = tokenizer.nextToken().toInt()
            d[it + 1] = d[it] + a[it + 1]
        }

        repeat(m) {
            tokenizer = StringTokenizer(bufferedReader.readLine())
            val i = tokenizer.nextToken().toInt()
            val j = tokenizer.nextToken().toInt()

            bufferedWriter.append((d[j] - d[i - 1]).toString()).appendLine()
        }
        bufferedWriter.use { it.flush() }
        bufferedReader.close()
    }
}

fun main() {
    BOJ11659().sum(System.`in`, System.out)
}