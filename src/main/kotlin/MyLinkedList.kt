package org.example

interface MyLinkedList<T> {
    fun length(): Int
    fun append(element: T)
    fun insert(element: T, index: Int)
    fun delete(index: Int): T
    fun deleteAll(element: T)
    fun get(index: Int): T
    fun clone(): MyLinkedList<T>
    fun reverse()
    fun findFirst(element: T): Int
    fun findLast(element: T): Int
    fun clear()
    fun extend(elements: List<T>)
}

class Node<T>(var value: T,
              var previous: Node<T>?,
              var next: Node<T>? = null) {
    fun isHead(): Boolean = previous == null
    fun isTail(): Boolean = next == null
}