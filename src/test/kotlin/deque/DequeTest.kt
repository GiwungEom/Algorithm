package deque

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DequeTest {

    @Test
    fun deque_front_add_front_remove_equals() {
        val deque = CustomDeque(10)

        repeat(10) {
            deque.addFront(it)
        }
        repeat(10) {
            assertEquals(9 - it, deque.removeFront())
        }
    }
    @Test
    fun deque_front_add_front_overflow() {
        val deque = CustomDeque(10)

        val exception = try {
            repeat(12) {
                deque.addFront(it)
            }
            false
        } catch (e: IndexOutOfBoundsException) {
            true
        }
        assertTrue(exception)
    }


    @Test
    fun deque_rear_add_rear_remove_equals() {
        val deque = CustomDeque(10)

        repeat(10) {
            deque.addRear(it)
        }
        repeat(10) {
            assertEquals(9 - it, deque.removeRear())
        }
    }

    @Test
    fun deque_front_add_rear_remove_equals() {
        val deque = CustomDeque(10)

        repeat(10) {
            deque.addFront(it)
        }
        repeat(10) {
            assertEquals(it, deque.removeRear())
        }
    }

    @Test
    fun deque_rear_add_front_remove_equals() {
        val deque = CustomDeque(10)

        repeat(10) {
            deque.addRear(it)
        }
        repeat(10) {
            assertEquals(it, deque.removeFront())
        }
    }

}