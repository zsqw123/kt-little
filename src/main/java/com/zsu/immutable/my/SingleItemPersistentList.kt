package com.zsu.immutable.my

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.implementations.immutableList.AbstractPersistentList
import kotlinx.collections.immutable.persistentListOf

class SingleItemPersistentList<E>(private val item: E) : AbstractPersistentList<E>() {
    private var _delegate: PersistentList<E>? = null
    private val delegate: PersistentList<E>
        get() = _delegate ?: persistentListOf(item).also { _delegate = it }

    override fun get(index: Int): E = if (index == 0) item else throw IndexOutOfBoundsException(index)
    override fun isEmpty(): Boolean = false
    override val size: Int get() = 1
    override fun clear(): PersistentList<E> = persistentListOf()
    override fun contains(element: E): Boolean = element == item

    // delegations
    override fun builder(): PersistentList.Builder<E> = delegate.builder()
    override fun add(index: Int, element: E): PersistentList<E> = delegate.add(index, element)
    override fun add(element: E): PersistentList<E> = delegate.add(element)
    override fun removeAt(index: Int): PersistentList<E> = delegate.removeAt(index)
    override fun set(index: Int, element: E): PersistentList<E> = delegate.set(index, element)
    override fun removeAll(predicate: (E) -> Boolean): PersistentList<E> = delegate.removeAll(predicate)
}

fun <E> listOfSingle(element: E): PersistentList<E> = SingleItemPersistentList(element)
