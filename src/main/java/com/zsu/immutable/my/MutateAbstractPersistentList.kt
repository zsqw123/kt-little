package com.zsu.immutable.my

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.implementations.immutableList.AbstractPersistentList
import kotlinx.collections.immutable.mutate

abstract class MutateAbstractPersistentList<E> : AbstractPersistentList<E>() {
    override fun add(index: Int, element: E): PersistentList<E> = mutate { it.add(index, element) }
    override fun add(element: E): PersistentList<E> = mutate { it.add(element) }
    override fun removeAt(index: Int): PersistentList<E> = mutate { it.removeAt(index) }
    override fun set(index: Int, element: E): PersistentList<E> = mutate { it[index] = element }
    override fun removeAll(predicate: (E) -> Boolean): PersistentList<E> = mutate { it.removeAll(predicate) }
}