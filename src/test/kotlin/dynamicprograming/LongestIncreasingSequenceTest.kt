package dynamicprograming

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class LongestIncreasingSequenceTest {

    @Test
    fun lisTest() {
        val expected = intArrayOf(0, 1, 2, 3, 4, 1, 1, 2, 4)
        val example = intArrayOf(0, 3, 5, 7, 9, 2, 1, 4, 8)
        val results = LisAlgorithm().calcLisCount(example)
        assertContentEquals(expected = expected, actual = results)
    }

    @Test
    fun sequenceSumTest() {
        val expected = 113
        val example = intArrayOf(1, 100, 2, 50, 60, 3, 5, 6, 7, 8)
        val result = LisAlgorithm().calcIsSum(example)
        assertEquals(expected = expected, actual = result)
    }
}