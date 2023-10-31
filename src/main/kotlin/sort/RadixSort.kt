package sort

import java.util.*

class RadixSort(private val array: IntArray) {

    fun sort(dataMaxLength: Int) {
        val board = Array<Queue<Int>>(size = 9) { LinkedList() }

        for (i in 0 until  dataMaxLength) {
            for (data in array) {
                board[data / Math.pow(10.toDouble(), i.toDouble()).toInt() % 10].add(data)
            }
            var index = 0
            for (queue in board) {
                while (queue.isNotEmpty()) {
                    array[index++] = queue.remove()
                }
            }
        }
    }
}
