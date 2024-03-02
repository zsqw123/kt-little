package com.zsu.immutable.my

import kotlinx.collections.immutable.PersistentList

class MyPersistentList<E>(
    override val size: Int,
    val allReferences: MutableList<MyPersistentList<E>>,
) : MutateAbstractPersistentList<E>() {

    override fun builder(): PersistentList.Builder<E> {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): E {
        TODO("Not yet implemented")
    }
}