package simulation

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DecimalTest {

    @Test
    fun decimalTest() {
        val result = to4DecimalNumber(1024)
        assertEquals(33333, result)
    }

    private fun to4DecimalNumber(decimal: Int): Int {
        val stringBuffer = StringBuffer()
        var brute = decimal - 1
        for (i in 0..4) {
            stringBuffer.append(brute % 4)
            brute = brute / 4
        }
        return stringBuffer.toString().toInt()
    }
}