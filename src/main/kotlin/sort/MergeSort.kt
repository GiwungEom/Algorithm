package sort

class MergeSort(
    private val array: IntArray
) {
    fun sort() {
        divide(0, array.lastIndex)
    }

    private fun divide(
        start: Int,
        last: Int
    ) {
        if (start >= last) return

        val mid = (start + last) / 2
        divide(start, mid)
        divide(mid + 1, last)
        mergeSort(start, mid, last)
    }

    private fun mergeSort(start: Int, mid: Int, last: Int) {
        val sortArray = IntArray(size = array.size)
        var pStart = start
        var pSecond = mid + 1
        var index = start
        while (pStart <= mid && pSecond <= last) {
            if (array[pStart] < array[pSecond]) {
                sortArray[index++] = array[pStart++]
            } else {
                sortArray[index++] = array[pSecond++]
            }
        }

        if (pStart > mid) {
            for (i in pSecond .. last) {
                sortArray[index++] = array[i]
            }
        } else {
            for (i in pStart .. mid) {
                sortArray[index++] = array[i]
            }
        }

        for (i in start .. last) {
            array[i] = sortArray[i]
        }
    }
}
