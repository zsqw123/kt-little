package com.zsu.duck

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun <reified T> constraintType(input: Any?) {
    contract { returns() implies (input is T) }
}

interface CatLike {
    fun meow()
}

inline fun <reified T> call(input: T) {
    constraintType<CatLike>(input)
    input.meow()
}

