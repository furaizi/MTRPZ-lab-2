import org.example.MyLinkedList
import org.example.MyNewLinkedListImpl

class MyNewLinkedListImplTest : AbstractMyLinkedListTest() {
    override fun <T> createList(): MyLinkedList<T> = MyNewLinkedListImpl<T>()
}