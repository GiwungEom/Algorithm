package sort

import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import kotlin.test.assertContentEquals

class QuickSortTest {

    private lateinit var array: IntArray

    @BeforeTest
    fun setUp() {
        array = intArrayOf(4,3,5,1,2,6,8,7)
    }

    @Test
    fun quickSortTest() {
        QuickSort(array).sort()
        assertContentEquals(intArrayOf(1,2,3,4,5,6,7,8), array)
    }
}