package simulation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


fun main() {

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    var tokenizer = StringTokenizer(bufferedReader.readLine())
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()
    var k = tokenizer.nextToken().toInt()
    var cnt = 0

    var r = 0
    var c = 0
    var paper = Array(size = 12) { IntArray(size = 12) }
    val note = Array(size = 42) { IntArray(size = 42) }

    fun rotate() {
        val tmp = Array(size = 12) { IntArray(size = 12) }

        for (i in 0 until r) {
            for (j in 0 until c) {
                tmp[i][j] = paper[i][j]
            }
        }

        for (i in 0 until c) {
            for (j in 0 until r) {
                paper[i][j] = tmp[r-1-j][i]
            }
        }

        val temp = r
        r = c
        c = temp
    }

    fun pastable(x: Int, y: Int): Boolean {
        for (i in 0 until r) {
            for (j in 0 until c) {
                if (note[x+i][y+j] == 1 && paper[i][j] == 1) {
                    return false
                }
            }
        }

        for (i in 0 until r) {
            for (j in 0 until c) {
                if (paper[i][j] == 1) {
                    note[x+i][y+j] = 1
                }
            }
        }

        return true
    }

    while (k-- != 0) {
        tokenizer = StringTokenizer(bufferedReader.readLine())
        r = tokenizer.nextToken().toInt()
        c = tokenizer.nextToken().toInt()

        repeat(r) { row ->
            tokenizer = StringTokenizer(bufferedReader.readLine())
            repeat(c) { col ->
                paper[row][col] = tokenizer.nextToken().toInt()
            }
        }

        for (rot in 0 until 4) {
            var is_paste = false
            for (x in 0 .. n-r) {
                if (is_paste) break
                for (y in 0..m-c) {
                    if (pastable(x, y)) {
                        is_paste = true
                        break
                    }
                }
            }

            if (is_paste) break
            rotate()
        }
    }

    for (i in 0 until n) {
        for (j in 0 until m) {
            cnt += note[i][j]
        }
    }

    bufferedWriter.append(cnt.toString()).flush()
    bufferedWriter.close()
    bufferedReader.close()
}


















