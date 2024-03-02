package com.zsu.immutable.my

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class MyPersistentList<E>(
    override val size: Int,
    private val allReferences: PersistentList<PersistentList<E>>,
    private val startIndices: PersistentList<Int>, // [startIndices] size always equals with [allReferences]
) : MutateAbstractPersistentList<E>() {

    constructor(size: Int, onlyOne: PersistentList<E>) : this(
        size, persistentListOf(onlyOne), persistentListOf(0),
    )

    override fun builder(): PersistentList.Builder<E> {
        return MyPersistentListBuilder(size, allReferences, startIndices)
    }

    override fun get(index: Int): E =
        findRef(allReferences, startIndices, index) { _, ref, offset -> ref[offset] }

    companion object {
        fun <E> copyOf(originList: List<E>): MyPersistentList<E> {
            return MyPersistentList(originList.size, originList.toPersistentList())
        }

        fun <E> of(): MyPersistentList<E> {
            return MyPersistentList(0, persistentListOf())
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

@OptIn(ExperimentalContracts::class)
inline fun <E, R> findRef(
    references: PersistentList<PersistentList<E>>,
    startIndices: PersistentList<Int>, index: Int,
    onRefFound: (refIndex: Int, ref: PersistentList<E>, offset: Int) -> R
): R {
    contract { callsInPlace(onRefFound, InvocationKind.EXACTLY_ONCE) }
    val refIndex = startIndices.refIndexOf(index)
    val startIndex = startIndices[refIndex]
    val ref = references[refIndex]
    val offset = index - startIndex
    return onRefFound(refIndex, ref, offset)
}