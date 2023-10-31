package sort

class QuickSort(private val array: IntArray) {
    fun sort() {
        quickSort(0, array.lastIndex)
    }

    private fun quickSort(left: Int, right: Int) {
        if (left <= right) {
            val pivot = partition(left, right)
            quickSort(left, pivot - 1)
            quickSort(pivot + 1, right)
        }
    }

    private fun partition(left: Int, right: Int): Int {
        var low = left + 1
        var high = right

        while (low <= high) {
            while (low <= high && array[left] >= array[low]) {
                low++
            }

            while (high >= left + 1 && array[left] <= array[high]) {
                high--
            }

            if (low <= high) {
                val temp = array[low]
                array[low] = array[high]
                array[high] = temp
            }
        }
        val temp = array[left]
        array[left] = array[high]
        array[high] = temp
        return high
    }
}
