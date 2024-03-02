package com.zsu.immutable.my

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

class MyPersistentList<E>(
    override val size: Int,
    val allReferences: PersistentList<PersistentList<E>>,
    val startIndices: PersistentList<Int>, // [startIndices] size always equals with [allReferences]
) : MutateAbstractPersistentList<E>() {

    constructor(size: Int, onlyOne: PersistentList<E>) : this(
        size, persistentListOf(onlyOne), persistentListOf(0),
    )

    override fun builder(): PersistentList.Builder<E> {
        return MyPersistentListBuilder(size, allReferences, startIndices)
    }

    override fun get(index: Int): E {
        val refIndex = startIndices.refIndexOf(index)
        val startIndex = startIndices[refIndex]
        val ref = allReferences[refIndex]
        return ref[index - startIndex]
    }

    companion object {
        fun <E> copyOf(originList: List<E>): MyPersistentList<E> {
            return MyPersistentList(originList.size, originList.toPersistentList())
        }
    }
}

fun PersistentList<Int>.refIndexOf(index: Int): Int {
    var low = -1
    var high = size
    while (low + 1 != high) {
        val mid = (low + high) ushr 1
        if (index >= get(mid)) high = mid
        else low = mid
    }
    return high
}