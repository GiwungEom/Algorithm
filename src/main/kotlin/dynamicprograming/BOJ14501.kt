package dynamicprograming

import java.io.*
import java.util.*
import kotlin.math.max

/**
 * 문제 풀이 접근을 뒤에서 부터 진행 해야
 * 쉽게 d[i] 비교 가능
 * d[i] = i 번째 날 가장 높은 수입
 * d[i] = max(d[i + t[i]] + p[i], d[i+1])
 */
class BOJ14501 {
    fun calc(inputStream: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))

        val n = bufferedReader.readLine().toInt()
        val d = IntArray(size = 20)
        val t = IntArray(size = 20)
        val p = IntArray(size = 20)

        repeat(n) {
            val tokenizer = StringTokenizer(bufferedReader.readLine())
            t[it + 1] = tokenizer.nextToken().toInt()
            p[it + 1] = tokenizer.nextToken().toInt()
        }

        for (i in n downTo 1) {
            if (i + t[i] <= n + 1) { // 상담 일자가 현재 날짜 안에 포함 되는지 판단. 상담 시작 당일 포함 함으로 1을 더한다
                d[i] = max(d[i + t[i]] + p[i], d[i + 1])
            } else { // 상담 날짜가 현재 날짜 안에 포함 되지 않을때는 d[i + 1] 값을 물려 받음
                d[i] = d[i + 1]
            }
        }

        bufferedWriter.use {
            it.append(d.max().toString())
            it.flush()
        }
        bufferedReader.close()
    }
}

fun main() {
    BOJ14501().calc(System.`in`, System.out)
}