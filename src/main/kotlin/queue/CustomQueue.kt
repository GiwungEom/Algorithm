package queue


class CustomQueue(size: Int = 10) {

    private val array: IntArray = IntArray(size)

    private var front = 0
    private var rear = 0
    var size = 0
        private set

    fun isEmpty() = front == rear
    fun isFull() = next(rear) == front

    fun next(index: Int): Int =
        if (index == array.lastIndex) 0
        else index + 1

    fun enqueue(data: Int) {
        if (isFull()) {
            throw IndexOutOfBoundsException("Queue is Full")
        }
        rear = next(rear)
        array[rear] = data
        size++
    }

    fun dequeue(): Int {
        if (isEmpty()) {
            throw NoSuchElementException("Queue is Empty")
        }
        front = next(front)
        return array[front].also { size-- }
    }

    fun front(): Int = array[next(front)]
    fun back(): Int = array[rear]
}