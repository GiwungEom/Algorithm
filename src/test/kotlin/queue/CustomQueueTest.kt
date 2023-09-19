package queue

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CustomQueueTest {

    @Test
    fun queue_enqueue_size_overflow_exception() {
        val customQueue = CustomQueue()
        val exception = try {
            repeat(10) {
                customQueue.enqueue(it)
            }
            false
        } catch (e: IndexOutOfBoundsException) {
            true
        }
        assertTrue(exception)
    }

    @Test
    fun queue_enqueue_size_full_equalsTrue() {
        val customQueue = CustomQueue()
        repeat(9) {
            customQueue.enqueue(it)
        }
        assertTrue(customQueue.isFull())
    }

    @Test
    fun queue_empty_equalsTrue() {
        val customQueue = CustomQueue()
        assertTrue(customQueue.isEmpty())
    }

    @Test
    fun queue_dequeue_equalsTrue() {
        val customQueue = CustomQueue()
        repeat(9) {
            customQueue.enqueue(it)
        }
        repeat(9) {
            assertEquals(customQueue.dequeue(), it)
        }
    }
    @Test
    fun queue_enqueue_equalsTrue() {
        val customQueue = CustomQueue(10)
        repeat(5) {
            repeat(8) {
                customQueue.enqueue(it)
            }
            println("queue.front() : ${customQueue.front()}, back() ${customQueue.back()}")
            repeat(8) {
                assertEquals(customQueue.dequeue(), it)
            }

            assertTrue(customQueue.isEmpty())
        }
    }
}