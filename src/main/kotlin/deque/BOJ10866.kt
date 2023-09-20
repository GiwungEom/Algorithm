package deque

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * 문제
 * 정수를 저장하는 덱(Deque)를 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.
 *
 * 명령은 총 여덟 가지이다.
 *
 * push_front X: 정수 X를 덱의 앞에 넣는다.
 * push_back X: 정수 X를 덱의 뒤에 넣는다.
 * pop_front: 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 * pop_back: 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 * size: 덱에 들어있는 정수의 개수를 출력한다.
 * empty: 덱이 비어있으면 1을, 아니면 0을 출력한다.
 * front: 덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 * back: 덱의 가장 뒤에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 * 입력
 * 첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 10,000)이 주어진다. 둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다.
 * 주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다. 문제에 나와있지 않은 명령이 주어지는 경우는 없다.
 *
 * 출력
 * 출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력한다.
 *
 * 예제 입력 1
 * 15
 * push_back 1
 * push_front 2
 * front
 * back
 * size
 * empty
 * pop_front
 * pop_back
 * pop_front
 * size
 * empty
 * pop_back
 * push_front 3
 * empty
 * front
 * 예제 출력 1
 * 2
 * 1
 * 2
 * 0
 * 2
 * 1
 * -1
 * 0
 * 1
 * -1
 * 0
 * 3
 * 예제 입력 2
 * 22
 * front
 * back
 * pop_front
 * pop_back
 * push_front 1
 * front
 * pop_back
 * push_back 2
 * back
 * pop_front
 * push_front 10
 * push_front 333
 * front
 * back
 * pop_back
 * pop_back
 * push_back 20
 * push_back 1234
 * front
 * back
 * pop_back
 * pop_back
 * 예제 출력 2
 * -1
 * -1
 * -1
 * -1
 * 1
 * 1
 * 2
 * 2
 * 333
 * 10
 * 10
 * 333
 * 20
 * 1234
 * 1234
 * 20
 */

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

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

    val customDeque = CustomDeque(10000)

    val n = bufferedReader.readLine().toInt()

    repeat(n) {
        val input = bufferedReader.readLine()
        val tokenizer = StringTokenizer(input)

        val output: String? = when (tokenizer.nextToken()) {
            "push_front" -> customDeque.addFront(tokenizer.nextToken().toInt()).run { null }
            "push_back" -> customDeque.addRear(tokenizer.nextToken().toInt()).run { null }
            "pop_front" -> if (customDeque.isEmpty) "-1" else customDeque.removeFront().toString()
            "pop_back" -> if (customDeque.isEmpty) "-1" else customDeque.removeRear().toString()
            "size" -> customDeque.size.toString()
            "empty" -> if (customDeque.isEmpty) "1" else "0"
            "front" -> if (customDeque.isEmpty) "-1" else customDeque.getFront().toString()
            "back" -> if (customDeque.isEmpty) "-1" else customDeque.getRear().toString()
            else -> null
        }

        output?.let { bufferedWriter.append(it).appendLine() }
    }

    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()

}