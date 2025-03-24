package org.example

interface MyLinkedList<T> {
    fun length(): Integer
    fun append(element: T)
    fun insert(element: T, index: Integer)
    fun delete(index: Integer): T
    fun deleteAll(element: T)
    fun get(index: Integer): T
    fun clone(): MyLinkedList<T>
    fun reverse()
    fun findFirst(element: T): Integer
    fun findLast(element: T): Integer
    fun clear()
    fun extend(elements: List<T>)
}