package com.zsu.immutable.my

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

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
        if (elements !is PersistentList) return addAll(elements.toPersistentList())
        val oldSize = size
        size += elements.size
        references = references.add(elements)
        indices = indices.add(oldSize)
        return true
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        if (elements.isEmpty()) return false
        if (elements !is PersistentList) return addAll(index, elements.toPersistentList())
        val oldSize = size
        val elementsSize = elements.size
        size += oldSize + elementsSize

        val indices = indices
        val refIndex = indices.refIndexOf(index)
        val startIndex = indices[refIndex]
        val ref = references[refIndex]
        val offset = index - startIndex
        // change current
        references = references.set(refIndex, ref.addAll(offset, elements))

        // make all start indices (which after the current index) plus elementsSize.
        for (i in (refIndex + 1) until indices.size) {
            this.indices = this.indices.set(i, indices[i] + elementsSize)
        }

        return true
    }

    override fun add(index: Int, element: E) {
        addAll(index, listOf(element))
    }

    override fun get(index: Int): E {
        val indices = indices
        val refIndex = indices.refIndexOf(index)
        val startIndex = indices[refIndex]
        val ref = references[refIndex]
        return ref[index - startIndex]
    }

    override fun removeAt(index: Int): E {
        val indices = indices
        val refIndex = indices.refIndexOf(index)
        val startIndex = indices[refIndex]
        val ref = references[refIndex]
        val offset = index - startIndex

        // change current
        val removed = ref[offset]
        references = references.set(refIndex, ref.removeAt(offset))

        // make all start indices (which after the current index) - 1.
        for (i in (refIndex + 1) until indices.size) {
            this.indices = this.indices.set(i, indices[i] - 1)
        }

        return removed
    }

    override fun set(index: Int, element: E): E {
        TODO("Not yet implemented")
    }

    override fun build(): MyPersistentList<E> {
        return MyPersistentList(size, references, indices)
    }
}