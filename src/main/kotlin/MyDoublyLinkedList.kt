package org.example

class MyDoublyLinkedList<T>(
    private var head: Node<T>? = null, private var tail: Node<T>? = null
): MyLinkedList<T>, Iterable<Node<T>> {

    override fun iterator(): Iterator<Node<T>> {
        return object : Iterator<Node<T>> {
            private var current: Node<T>? = head

            override fun hasNext() = current != null

            override fun next(): Node<T> {
                if (!hasNext()) throw NoSuchElementException()
                val node = current!!
                current = current?.next
                return node
            }
        }
    }

    fun MyDoublyLinkedList<T>.withIndex(): Sequence<Pair<Int, Node<T>>> = sequence {
        var index = 0
        for (node in this@withIndex) {
            yield(index to node)
            index++
        }
    }

    override fun length(): Int {
        var count = 0
        for (node in this)
            count++
        return count
    }

    override fun append(element: T) {
        val newNode = Node(element, tail)
        if (tail != null)
            tail?.next = newNode
        else
            head = newNode
        tail = newNode
    }

    // insert element before index
    override fun insert(element: T, index: Int) {
        val found = getNode(index)
        val newNode = Node(element, found.previous, found)
        if (found.isHead()) {
            this.head = newNode
        }
        else {
            val beforeFound = found.previous
            beforeFound?.next = newNode
        }
        found.previous = newNode
    }

    override fun delete(index: Int): T {
        val found = getNode(index)
        deleteNode(found)
        return found.value
    }

    override fun deleteAll(element: T) {
        for (node in this) {
            if (node.value == element)
                deleteNode(node)
        }
    }

    override fun get(index: Int): T = getNode(index).value

    override fun clone(): MyLinkedList<T> {
        val newList = MyDoublyLinkedList<T>()
        for (node in this) {
            newList.append(node.value)
        }
        return newList
    }

    override fun reverse() {
        var current = head
        head = tail.also { tail = head }
        while (current != null) {
            val nextNode = current.next
            current.next = current.previous
            current.previous = nextNode
            current = nextNode
        }
    }

    override fun findFirst(element: T): Int {
        for ((index, node) in this.withIndex()) {
            if (node.value == element)
                return index
        }
        return -1
    }

    override fun findLast(element: T): Int {
        var lastOccurrence = -1
        for ((index, node) in this.withIndex()) {
            if (node.value == element)
                lastOccurrence = index
        }
        return lastOccurrence
    }

    override fun clear() {
        deleteNodeRecursively(head!!)
        head = null
        tail = null
    }

    override fun extend(elements: List<T>) {
        val newList = MyDoublyLinkedList<T>()
        for (element in elements)
            newList.append(element)

        this.tail?.next = newList.head
        newList.head?.previous = this.tail
        this.tail = newList.tail
    }

    private fun getNode(index: Int): Node<T> {
        if (index < 0) throw IndexOutOfBoundsException()
        for ((i, node) in this.withIndex()) {
            if (i == index)
                return node
        }
        throw IndexOutOfBoundsException()
    }

    private fun deleteNodeRecursively(node: Node<T>?) {
        if (node?.next != null) {
            deleteNodeRecursively(node.next)
            node.next = null
        }
        node?.previous = null
    }

    private fun deleteNode(node: Node<T>) {
        val (beforeFound, afterFound) = node.previous to node.next

        beforeFound?.next = afterFound
        afterFound?.previous = beforeFound

        when {
            node.isHead() -> this.head = afterFound
            node.isTail() -> this.tail = beforeFound
        }

        node.previous = null
        node.next = null
    }
}
