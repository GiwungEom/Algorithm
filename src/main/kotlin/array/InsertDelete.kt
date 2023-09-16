package array

fun main() {

    fun Array<Int?>.insertExtraVariable(index: Int, data: Int) {
        var prevData = this[index]
        for (i in index until size - 1) {
            val nextData = this[i + 1]
            this[i + 1] = prevData
            prevData = nextData
        }
        this[index] = data
    }

    fun Array<Int?>.insert(index: Int, data: Int) {
        for (i in lastIndex - 1 downTo index) {
            this[i + 1] = this[i]
        }
        this[index] = data
    }

    fun Array<Int?>.delete(index: Int) {
        for (i in index + 1 until size) {
            this[i - 1] = this[i]
            this[i] = null
        }
    }

    fun insertTest() {
        val array = Array<Int?>(10) { null }
        array[0] = 10
        array[1] = 20
        array[2] = 30

//    insert(3, 40, arr, len); // 10 20 30 40
//    printArr(arr, len);
//    insert(1, 50, arr, len); // 10 50 20 30 40
//    printArr(arr, len);
//    insert(0, 15, arr, len); // 15 10 50 20 30 40
//    printArr(arr, len);

        println(array.joinToString())
//        array.insertExtraVariable(3, 40)
        array.insert(3, 40)
        println(array.joinToString())
//        array.insertExtraVariable(1, 50)
        array.insert(1, 50)
        println(array.joinToString())
//        array.insertExtraVariable(0, 15)
        array.insert(0, 15)
        println(array.joinToString())
    }

    fun deleteTest() {
//        int arr[10] = {10, 50, 40, 30, 70, 20};
//        int len = 6;
//        erase(4, arr, len); // 10 50 40 30 20
//        printArr(arr, len);
//        erase(1, arr, len); // 10 40 30 20
//        printArr(arr, len);
//        erase(3, arr, len); // 10 40 30
//        printArr(arr, len);

        val array = Array<Int?>(10) { null }
        array[0] = 10
        array[1] = 50
        array[2] = 40
        array[3] = 30
        array[4] = 70
        array[5] = 20

        println(array.joinToString())
        array.delete(4)
        println(array.joinToString())
        array.delete(1)
        println(array.joinToString())
        array.delete(3)
        println(array.joinToString())
    }

    insertTest()
    println()
    deleteTest()
}