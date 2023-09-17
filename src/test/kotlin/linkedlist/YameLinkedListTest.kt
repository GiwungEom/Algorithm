package linkedlist

import org.junit.jupiter.api.Test

class YameLinkedListTest {

    /**
     * void insert_test(){
     *   cout << "****** insert_test *****\n";
     *   insert(0, 10); // 10(address=1)
     *   traverse();
     *   insert(0, 30); // 30(address=2) 10
     *   traverse();
     *   insert(2, 40); // 30 40(address=3) 10
     *   traverse();
     *   insert(1, 20); // 30 40 10 20(address=4)
     *   traverse();
     *   insert(4, 70); // 30 40 10 20 70(address=5)
     *   traverse();
     * }
     *
     * void erase_test(){
     *   cout << "****** erase_test *****\n";
     *   erase(1); // 30 40 20 70
     *   traverse();
     *   erase(2); // 40 20 70
     *   traverse();
     *   erase(4); // 40 70
     *   traverse();
     *   erase(5); // 40
     *   traverse();
     * }
     */
    @Test
    fun linkedlist_insert() {
        with(YameLinkedList()) {
            insert(0, 10); // 10(address=1)
            traverse()
            insert(0, 30); // 30(address=2) 10
            traverse()
            insert(2, 40); // 30 40(address=3) 10
            traverse()
            insert(1, 20); // 30 40 10 20(address=4)
            traverse()
            insert(4, 70); // 30 40 10 20 70(address=5)
            traverse()
        }
    }

    @Test
    fun linkedlist_erase() {
        with(YameLinkedList()) {
            insert(0, 10); // 10(address=1)
            insert(0, 30); // 30(address=2) 10
            insert(2, 40); // 30 40(address=3) 10
            insert(1, 20); // 30 40 10 20(address=4)
            insert(4, 70); // 30 40 10 20 70(address=5)

            erase(1); // 30 40 20 70
            traverse();
            erase(2); // 40 20 70
            traverse();
            erase(4); // 40 70
            traverse();
            erase(5); // 40
            traverse();
        }
    }
}