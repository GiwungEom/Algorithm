package linkedlist

/**
 * Linked List 노드를 사용한 구현 대신 시간 단축을 위해
 * 3개의 배열을 이용한 차선책
 * 각 배열의 0 번째 인덱스는 DummyNode 로 한다.
 */
class YameLinkedList {

    private val maxSize = 1000005

    // data array
    private val data = IntArray(maxSize) { -1 }
    // prev node array
    private val prev = IntArray(maxSize) { -1 }
    // next node array
    private val next = IntArray(maxSize) { -1 }
    // next index
    private var unusedIndex = 1

    /**
     * 특정 요소 뒤 데이터 삽입
     * index : 데이터 삽입 위치 배열 인덱스
     */
    fun insert(index: Int, num: Int) {
        data[unusedIndex] = num
        prev[unusedIndex] = index
        next[unusedIndex] = next[index]

        if (next[index] != -1) {
            prev[next[index]] = unusedIndex
        }
        next[index] = unusedIndex
        unusedIndex++
    }

    /**
     * 특정 요소 데이터 삭제
     * index : 데이터 삽입 위치 배열 인덱스
     */
    fun erase(index: Int) {
        next[prev[index]] = next[index]
        if (next[index] != -1) {
            prev[next[index]] = prev[index]
        }

        data[index] = -1
        prev[index] = -1
        next[index] = -1
    }

    fun traverse() {
        val stringBuffer = StringBuffer()
        var curIndex = 0
        while (curIndex != -1) {
            if (curIndex != 0) {
                stringBuffer.append(data[curIndex]).append(" ")
            }
            curIndex = next[curIndex]
        }
        println(stringBuffer.toString())
    }
}