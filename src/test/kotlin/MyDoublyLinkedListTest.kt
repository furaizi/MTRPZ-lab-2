import org.example.MyDoublyLinkedList
import org.example.MyLinkedList

class MyDoublyLinkedListTest : AbstractMyLinkedListTest() {
    override fun <T> createList(): MyLinkedList<T> = MyDoublyLinkedList<T>()
}