package backtracking

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer


fun main() {

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    var tokenizer = StringTokenizer(bufferedReader.readLine())
    val n = tokenizer.nextToken().toInt()
    val s = tokenizer.nextToken().toInt()

    var cnt = 0
    val arr = IntArray(size = n)

    tokenizer = StringTokenizer(bufferedReader.readLine())
    repeat(n) {
        arr[it] = tokenizer.nextToken().toInt()
    }

    fun func(num: Int, sum: Int) {
        if (n == num) {
            if (s == sum) cnt++
            return
        }

        func(num + 1, sum)
        func(num + 1, sum + arr[num])
    }

    func(0, 0)
    if (s == 0) cnt--

    bufferedWriter.append(cnt.toString()).flush()
    bufferedWriter.close()
    bufferedReader.close()
}