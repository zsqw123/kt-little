package com.zsu.immutable.my

import kotlinx.collections.immutable.PersistentList

class MyPersistentListBuilder<E>(
    originSize: Int,
    originReferences: PersistentList<PersistentList<E>>,
    originIndices: PersistentList<Int>,
) : AbstractMutableList<E>(), PersistentList.Builder<E> {
    override var size: Int = originSize
    private var references = originReferences
    private var indices = originIndices

    override fun addAll(elements: Collection<E>): Boolean {
        if (elements.isEmpty()) return false
        if (elements !is PersistentList) return super.addAll(elements)
        val oldSize = size
        size += elements.size
        references = references.add(elements)
        indices = indices.add(oldSize)
        return true
    }

    override fun add(index: Int, element: E) {

        TODO("Not yet implemented")
    }

    override fun build(): PersistentList<E> {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): E {
        TODO("Not yet implemented")
    }

    override fun removeAt(index: Int): E {
        TODO("Not yet implemented")
    }

    override fun set(index: Int, element: E): E {
        TODO("Not yet implemented")
    }
}