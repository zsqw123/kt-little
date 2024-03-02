package com.zsu.immutable.my

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

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
        findRef(index) { refIndex, ref, offset ->
            // change current
            references = references.set(refIndex, ref.addAll(offset, elements))

            // make all start indices (which after the current index) plus elementsSize.
            for (i in (refIndex + 1) until indices.size) {
                this.indices = this.indices.set(i, indices[i] + elementsSize)
            }
        }

        return true
    }

    override fun add(index: Int, element: E) {
        addAll(index, listOf(element))
    }

    override fun get(index: Int): E =
        findRef(index) { _, ref, offset -> ref[offset] }

    override fun removeAt(index: Int): E = findRef(index) { refIndex, ref, offset ->
        // change current
        val removed = ref[offset]
        references = references.set(refIndex, ref.removeAt(offset))
        // make all start indices (which after the current index) - 1.
        for (i in (refIndex + 1) until indices.size) {
            this.indices = this.indices.set(i, indices[i] - 1)
        }
        removed
    }

    override fun set(index: Int, element: E): E = findRef(index) { refIndex, ref, offset ->
        val origin = ref[offset]
        references = references.set(refIndex, ref.set(index, element))
        origin
    }

    @OptIn(ExperimentalContracts::class)
    private inline fun <R> findRef(
        index: Int,
        onRefFound: (refIndex: Int, ref: PersistentList<E>, offset: Int) -> R
    ): R {
        contract { callsInPlace(onRefFound, InvocationKind.EXACTLY_ONCE) }
        return findRef(references, indices, index, onRefFound)
    }

    override fun build(): MyPersistentList<E> {
        return MyPersistentList(size, references, indices)
    }
}