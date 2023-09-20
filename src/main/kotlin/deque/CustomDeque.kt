package deque

import java.util.NoSuchElementException

class CustomDeque(size: Int) {

    val array = IntArray(size * 2 + 1) // front back 둘다 한번에 다 넣기 편하게 짠 영역 (좋은 소스 같아 보이지는 않으나, 간편한 구현을 위한 임시방편)

    // empty ==> front rear 가 같을 경우 empty

    private var front: Int = size
    private var rear: Int = size

    val isEmpty: Boolean
        get() = front == rear

    val size: Int
        get() = rear - front

    fun getFront(): Int {
        return array[front + 1]
    }

    fun getRear(): Int {
        return array[rear]
    }

    fun addFront(data: Int) {
        if (front == -1) {
            throw IndexOutOfBoundsException()
        }
        array[front--] = data
    }

    fun removeFront(): Int {
        if (isEmpty) {
            throw NoSuchElementException()
        }
        return array[++front]
    }

    fun addRear(data: Int) {
        if (rear == array.lastIndex) {
            throw IndexOutOfBoundsException()
        }
        array[++rear] = data
    }

    fun removeRear(): Int {
        return array[rear--]
    }
}