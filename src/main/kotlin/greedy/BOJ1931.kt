package greedy

import java.io.*
import java.util.*

class BOJ1931 {

    fun findNextSchedule(
        input: List<Pair<Int, Int>>
    ): List<Pair<Int, Int>> {
        val list = ArrayList<Pair<Int, Int>>()
        var t = 0
        for (i in 0 .. input.lastIndex) {
            if (t > input[i].first) continue
            list.add(input[i])
            t = input[i].second
        }
        return list
    }

    fun calc(inputStream: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))

        val n = bufferedReader.readLine().toInt()
        val schedule = ArrayList<Pair<Int, Int>>()
        repeat(n) {
            with(StringTokenizer(bufferedReader.readLine())) {
                schedule.add(nextToken().toInt() to nextToken().toInt())
            }
        }

        schedule.sortWith { a, b ->
            if (a.second - b.second == 0) {
                a.first - b.first
            } else {
                a.second - b.second
            }
        }

        bufferedWriter.use {
            it.append(findNextSchedule(input = schedule).size.toString())
            it.flush()
        }
        bufferedReader.close()
    }
}

fun main() {
    BOJ1931().calc(System.`in`, System.out)
}