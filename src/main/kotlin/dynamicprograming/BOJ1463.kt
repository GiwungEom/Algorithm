package dynamicprograming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main() {

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val n = bufferedReader.readLine().toInt()
    val d = IntArray(size = 1000005)
    d[1] = 0
    for (i in 2 .. n) {
        d[i] = d[i - 1] + 1
        if (i % 2 == 0) d[i] = min(d[i], d[i/2] + 1)
        if (i % 3 == 0) d[i] = min(d[i], d[i/3] + 1)
    }
    bufferedWriter.use {
        it.append(d[n].toString()).flush()
    }
    bufferedReader.close()
}