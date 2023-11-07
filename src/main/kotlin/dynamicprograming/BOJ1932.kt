package dynamicprograming

import java.io.*
import java.util.*
import kotlin.math.max

class BOJ1932 {
    fun calc(inputStream: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))

        var arraySize = 1
        val preSum = Array(size = 505) { IntArray(size = arraySize++) }
        val n = bufferedReader.readLine().toInt()
        repeat(n) {
            val nIndex = it + 1
            val prevIndex = it
            val tokenizer = StringTokenizer(bufferedReader.readLine())
            if (nIndex == 1) {
                preSum[1][1] = tokenizer.nextToken().toInt()
            } else {
                val temp = IntArray(size = nIndex + 1)
                repeat(nIndex) { index ->
                    temp[index + 1] = tokenizer.nextToken().toInt()
                }

                for (i in 1..prevIndex) {
                    preSum[nIndex][i] = max(preSum[nIndex][i], preSum[prevIndex][i] + temp[i])
                    preSum[nIndex][i + 1] = max(preSum[nIndex][i + 1], preSum[prevIndex][i] + temp[i + 1])
                }
            }
        }

        bufferedWriter.use {
            it.append(preSum[n].max().toString()).flush()
        }
        bufferedReader.close()
    }
}

fun main() {
    BOJ1932().calc(System.`in`, System.out)
}