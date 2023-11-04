package dynamicprograming

import java.io.*
import kotlin.math.max

fun main() {
    BOJ2579().calc(System.`in`, System.out)
}

class BOJ2579 {
    fun calc(input: InputStream, output: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(input))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(output))

        val n = bufferedReader.readLine().toInt()
        val steps = IntArray(size = n + 1)
        val d = Array(size = n + 1) { IntArray(size = 3) }

        repeat(n) {
            steps[it + 1] = bufferedReader.readLine().toInt()
        }

        if (n == 1) {
            bufferedWriter.use {
                it.append(steps[1].toString()).flush()
                it.close()
            }
            return
        }

        d[1][1] = steps[1]
        d[1][2] = 0
        d[2][1] = steps[2]
        d[2][2] = d[1][1] + steps[2]

        for (i in 3..n) {
            d[i][1] = max(d[i - 2][1], d[i - 2][2]) + steps[i]
            d[i][2] = d[i - 1][1] + steps[i]
        }
        bufferedWriter.use {
            it.append(max(d[n][1], d[n][2]).toString()).flush()
        }
        bufferedReader.close()
    }
}
