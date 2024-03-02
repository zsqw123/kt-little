package com.zsu.immutable.my

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

class MyPersistentList<E>(
    override val size: Int,
    val allReferences: PersistentList<PersistentList<E>>,
    val startIndices: PersistentList<Int>, // [startIndices] size always equals with [allReferences]
) : MutateAbstractPersistentList<E>() {

    constructor(size: Int, current: PersistentList<E>) : this(
        size, persistentListOf(current), persistentListOf(0),
    )

    override fun builder(): PersistentList.Builder<E> {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): E {
        val refIndex = refIndexOf(index)
        val startIndex = startIndices[refIndex]
        val ref = allReferences[refIndex]
        return ref[index - startIndex]
    }

    private fun refIndexOf(index: Int): Int {
        var low = -1
        var high = startIndices.size
        while (low + 1 != high) {
            val mid = (low + high) ushr 1
            if (index >= startIndices[mid]) high = mid
            else low = mid
        }
        return high
    }

    companion object {
        fun <E> copyOf(originList: List<E>): MyPersistentList<E> {
            return MyPersistentList(originList.size, originList.toPersistentList())
        }
    }
}
