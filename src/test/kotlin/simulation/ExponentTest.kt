package simulation

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExponentTest {

    @Test
    fun exponentRelationTest() {
        assertEquals(4, 1.shl(2 * 1))
        assertEquals(4 * 4, 1.shl(2 * 2))
        assertEquals(4 * 4 * 4, 1.shl(2 * 3))
        assertEquals(4 * 4 * 4 * 4, 1.shl(2 * 4))
    }
}