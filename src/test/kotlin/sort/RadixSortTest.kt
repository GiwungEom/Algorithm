package sort

import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class RadixSortTest {

    private lateinit var array: IntArray
    @BeforeTest
    fun setUp() {
        array = intArrayOf(444, 213, 413, 523, 123)
    }

    @Test
    fun numberPositionTest() {
        assertEquals((array[4] / Math.pow(10.toDouble(), 0.toDouble()).toInt()) % 10, 3)
        assertEquals((array[4] / Math.pow(10.toDouble(), 1.toDouble()).toInt()) % 10, 2)
        assertEquals((array[4] / Math.pow(10.toDouble(), 2.toDouble()).toInt()) % 10, 1)
    }

    @Test
    fun radixSortTest() {
        RadixSort(array).sort(dataMaxLength = 3)
        assertContentEquals(intArrayOf(123, 213, 413, 444, 523), array)
    }
}