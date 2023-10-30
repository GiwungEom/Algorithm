package simulation

import java.io.*
import java.util.*

class BOJ14891 {

    // way - false left, true right
    fun rotateGear(gear: Deque<Boolean>, way: Boolean) {
        if (!way) {
            gear.addLast(gear.removeFirst())
        } else {
            gear.addFirst(gear.removeLast())
        }
    }

    fun rotateGearConnection(
        gearNumber: Int,
        gears: Array<Deque<Boolean>>,
        way: Boolean
    ) {
        if (gearNumber > 0) {
            var currentWay = way
            for (i in gearNumber - 1 downTo 0) {
                if (gears[i].elementAt(2) != gears[i+1].elementAt(6)) {
                    currentWay = !currentWay
                    rotateGear(gears[i], currentWay)
                } else {
                    break
                }
            }
        }

        if (gearNumber < 3) {
            var currentWay = way
            for (i in gearNumber + 1 .. 3) {
                if (gears[i].elementAt(6) != gears[i-1].elementAt(2)) {
                    currentWay = !currentWay
                    rotateGear(gears[i], currentWay)
                } else {
                    break
                }
            }
        }
    }

    fun gear(input: InputStream, outputStream: OutputStream) {
        val bufferedReader = BufferedReader(InputStreamReader(input))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))
        val gears = Array<Deque<Boolean>>(size = 4) { LinkedList() }

        bufferedReader.use {
            repeat(4) { row ->
                val line = it.readLine().toCharArray()
                repeat(8) { col ->
                    gears[row].addLast(line[col] == '1')
                }
            }

            val count = it.readLine().toInt()
            repeat(count) { _ ->
                val tokenizer = StringTokenizer(it.readLine())
                val gearNumber = tokenizer.nextToken().toInt() - 1
                val way = tokenizer.nextToken().toInt() == 1

                rotateGearConnection(gearNumber, gears, way)
                rotateGear(gears[gearNumber], way)
            }
        }

        bufferedWriter.use {
            var sum = 0
            gears.forEachIndexed { index, deque ->
                sum += if (deque.elementAt(0)) 1.shl(index) else 0
            }
            it.append(sum.toString()).flush()
        }
    }
}

fun main() {
    BOJ14891().gear(System.`in`, System.out)
}