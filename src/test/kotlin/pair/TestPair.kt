package pair

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class TestPair {
    @Test
    fun pair_equalsTrue() {
        assertEquals(Pair(1,1), Pair(1,1))
        assertNotEquals(Pair(1,1), Pair(1,2))
    }
}