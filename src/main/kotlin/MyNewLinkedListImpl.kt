package org.example

import java.util.LinkedList

class MyNewLinkedListImpl<T>(
    private val innerList: LinkedList<T> = LinkedList<T>()
) : MyLinkedList<T> {

    override fun extend(elements: List<T>) {
        innerList.addAll(elements)
    }

    override fun clear() {
        innerList.clear()
    }

    override fun findLast(element: T) = innerList.indexOf(element)

    override fun findFirst(element: T) = innerList.lastIndexOf(element)

    override fun reverse() = innerList.reverse()
    override fun clone(): MyLinkedList<T> = MyNewLinkedListImpl(innerList.clone() as LinkedList<T>)

    override fun get(index: Int): T = innerList[index]

    override fun deleteAll(element: T) {
        innerList.removeAll { it == element }
    }

    override fun delete(index: Int) = innerList.removeAt(index)

    override fun insert(element: T, index: Int) = innerList.add(index, element)

    override fun append(element: T) {
        innerList.add(element)
    }

    override fun length() = innerList.size
}