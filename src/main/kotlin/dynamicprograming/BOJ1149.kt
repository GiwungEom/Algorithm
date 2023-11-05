package dynamicprograming

import java.io.*
import java.util.*
import kotlin.math.min

class BOJ1149 {
    fun calc(inputStream: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))

        val n = bufferedReader.readLine().toInt()
        val r = IntArray(size = n + 1)
        val g = IntArray(size = n + 1)
        val b = IntArray(size = n + 1)
        val d = Array(size = n + 1) { IntArray(size = 3) }

        repeat(n) {
            val tokenizer = StringTokenizer(bufferedReader.readLine())
            repeat(3) { index ->
                if (index == 0) {
                    r[it + 1] = tokenizer.nextToken().toInt()
                } else if (index == 1) {
                    g[it + 1] = tokenizer.nextToken().toInt()
                } else {
                    b[it + 1] = tokenizer.nextToken().toInt()
                }
            }
        }

        d[1][0] = r[1]
        d[1][1] = g[1]
        d[1][2] = b[1]

        for (k in 2..n) {
            d[k][0] = min(d[k-1][1], d[k-1][2]) + r[k]
            d[k][1] = min(d[k-1][0], d[k-1][2]) + g[k]
            d[k][2] = min(d[k-1][0], d[k-1][1]) + b[k]
        }

        bufferedWriter.use {
            it.append(min(min(d[n][0], d[n][1]), d[n][2]).toString()).flush()
        }
        bufferedReader.close()
    }
}

fun main() {
    BOJ1149().calc(System.`in`, System.out)
}