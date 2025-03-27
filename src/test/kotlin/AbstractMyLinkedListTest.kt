import org.example.MyDoublyLinkedList
import org.example.MyLinkedList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.fail

abstract class AbstractMyLinkedListTest {
    protected abstract fun <T> createList(): MyLinkedList<T>
    protected lateinit var list: MyLinkedList<Char>

    @BeforeEach
    fun setUp() {
        list = createList()
    }

    @Test
    fun `get valid index`() {
        list.append('X')
        assertEquals('X', list.get(0))
    }

    @Test
    fun `findFirst and findLast`() {
        list.append('A')
        list.append('B')
        list.append('A')
        assertEquals(0, list.findFirst('A'))
        assertEquals(2, list.findLast('A'))
        assertEquals(-1, list.findFirst('C'))
    }

    @Test
    fun `append to empty list`() {
        list.append('a')
        assertEquals('a', list.get(0))
        assertTrue(list.length() == 1)
    }

    @Test
    fun `append multiple elements`() {
        val range = CharRange('a', 'c')
        range.forEach { list.append(it) }
        assertEquals(range.count(), list.length())
        range.forEachIndexed { i, char -> assertEquals(char, list.get(i)) }
    }

    @Test
    fun `insert at beginning`() {
        list.append('b')
        list.insert('a', 0)
        assertEquals('a', list.get(0))
        assertEquals('b', list.get(1))
        assertEquals(2, list.length())
    }

    @Test
    fun `insert in middle`() {
        list.append('a')
        list.append('c')
        list.insert('b', 1)
        assertEquals('b', list.get(1))
        assertEquals('c', list.get(2))
        assertEquals(3, list.length())
    }

    @Test
    fun `insert invalid index throws exception`() {
        assertThrows<IndexOutOfBoundsException> { list.insert('A', 1) }
        list.append('A')
        assertThrows<IndexOutOfBoundsException> { list.insert('B', 2) }
    }

    @Test
    fun `delete from empty list throws exception`() {
        assertThrows<IndexOutOfBoundsException> { list.delete(0) }
    }

    @Test
    fun `delete single element`() {
        list.append('A')
        val deleted = list.delete(0)
        assertEquals('A', deleted)
        assertEquals(0, list.length())
        assertEquals(-1, list.findFirst('A'))
    }

    @Test
    fun `delete first element`() {
        list.append('A')
        list.append('B')
        list.append('C')
        list.delete(0)
        assertEquals('B', list.get(0))
        assertEquals(2, list.length())
    }

    @Test
    fun `delete last element`() {
        list.append('A')
        list.append('B')
        list.delete(1)
        assertEquals(1, list.length())
        assertEquals('A', list.get(0))
    }

    @Test
    fun `delete middle element`() {
        list.append('A')
        list.append('B')
        list.append('C')
        list.delete(1)
        assertEquals('A', list.get(0))
        assertEquals('C', list.get(1))
        assertEquals(2, list.length())
    }

    @Test
    fun `deleteAll elements`() {
        list.append('A')
        list.append('B')
        list.append('A')
        list.deleteAll('A')
        assertEquals(1, list.length())
        assertEquals('B', list.get(0))
    }

    @Test
    fun `clone creates independent list`() {
        list.append('A')
        list.append('B')
        val clone = list.clone()
        clone.append('C')
        assertEquals(2, list.length())
        assertEquals(3, clone.length())
    }

    @Test
    fun `reverse list`() {
        val range = CharRange('a', 'c')
        range.forEach { list.append(it) }
        list.reverse()
        val reversed = range.reversed()
        reversed.forEachIndexed { i, char -> assertEquals(char, list.get(i)) }
    }

    @Test
    fun `clear list`() {
        list.append('A')
        list.append('B')
        list.clear()
        assertEquals(0, list.length())
    }

    @Test
    fun `extend lists`() {
        list.append('A')
        list.extend(listOf('B', 'C'))
        assertEquals(3, list.length())
        assertEquals('B', list.get(1))
        assertEquals('C', list.get(2))
    }

    @Test
    fun failingTest() {
        fail { "demo" }
    }
}