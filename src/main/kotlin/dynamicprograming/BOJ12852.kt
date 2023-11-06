package dynamicprograming

import java.io.*

class BOJ12852 {
    fun calc(inputStream: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))

        val n = bufferedReader.readLine().toInt()
        val d = IntArray(size = 1000005)
        val pre = IntArray(size = 1000005)

        d[1] = 0
        pre[1] = 0

        for (i in 2..n) {
            d[i] = d[i - 1] + 1
            pre[i] = i - 1
            if (i % 2 == 0 && d[i / 2] + 1 < d[i]) {
                d[i] = d[i / 2] + 1
                pre[i] = i / 2
            }
            if (i % 3 == 0 && d[i / 3] + 1 < d[i]) {
                d[i] = d[i / 3] + 1
                pre[i] = i / 3
            }
        }

        bufferedWriter.append(d[n].toString()).appendLine()
        var index = n
        bufferedWriter.append(index.toString()).append(" ")
        while (true) {
            if (index == 1) break
            index = pre[index]
            bufferedWriter.append(index.toString()).append(" ")
        }

        bufferedWriter.use {
            it.flush()
            it.close()
        }
        bufferedReader.close()
    }
}

fun main() {
    BOJ12852().calc(System.`in`, System.out)
}