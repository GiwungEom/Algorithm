package backtracking

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val tokenizer = StringTokenizer(bufferedReader.readLine())
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

    val arr = IntArray(size = n)
    val isUsed = BooleanArray(size = n + 1)

    fun func(k: Int) {
        if (k == m) {
            for (i in 0 until m) {
                bufferedWriter.append(arr[i].toString()).append(" ")
            }
            bufferedWriter.appendLine()
        }

        val st = if (k != 0) arr[k - 1] + 1 else 1
        for (i in st .. n) {
            if (!isUsed[i]) {
                arr[k] = i
                isUsed[i] = true
                func(k + 1)
                isUsed[i] = false
            }
        }
    }

    func(0)
    bufferedWriter.close()
    bufferedReader.close()
}



