package dynamicprograming

import java.io.*
import java.util.*
import kotlin.math.max

class BOJ11053 {
    fun calc(inputStream: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))

        val n = bufferedReader.readLine().toInt()
        val tokenizer = StringTokenizer(bufferedReader.readLine())
        val a = IntArray(size = 1005)
        val d = IntArray(size = 1005)

        repeat(n) {
            a[it] = tokenizer.nextToken().toInt()
        }

        for (i in 0 until n) {
            for (j in 0 until i) {
                if (a[j] < a[i]) {
                    d[i] = max(d[i], d[j])
                }
            }
            d[i] += 1
        }

        bufferedWriter.use {
            it.append(d.max().toString())
            it.flush()
        }

        bufferedReader.close()
    }
}

fun main() {
    BOJ11053().calc(System.`in`, System.out)
}