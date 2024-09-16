package com.zsu

fun main() {
    val l = listOf(1, 2, 3, 4, 5)
    l.map {
        if (it % 2 == 0) throw Exception() else it
    }
}

fun <T, R> List<T>.process(f: (T) -> R): Result<List<R>> = Result.success(map(f))