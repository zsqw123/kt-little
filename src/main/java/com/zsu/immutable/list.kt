package com.zsu.immutable

import com.google.common.collect.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.plus
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() {
    val random = Random(System.currentTimeMillis())
    val payload = List(10000) {
        List(random.nextInt(8, 200)) { random.nextInt() }
    }
    println("listPlus cost ${measureTimeMillis { listPlus(payload) }}ms")
    println("flatten cost ${measureTimeMillis { flatten(payload) }}ms")
    println("kt immutable cost ${measureTimeMillis { ktImmutable(payload) }}ms")
    println("guava immutable cost ${measureTimeMillis { guavaImmutable(payload) }}ms")
}

fun <T> listPlus(payload: List<List<T>>): List<T> {
    var current = emptyList<T>()
    payload.forEach { current = current + it }
    return current
}

fun <T> flatten(payload: List<List<T>>): List<T> = payload.flatten()

fun <T> ktImmutable(input: List<List<T>>): List<T> {
    var current = persistentListOf<T>()
    input.forEach { current += it }
    return current
}

fun <T> guavaImmutable(input: List<List<T>>): List<T> {
    var current: ImmutableList<T> = ImmutableList.of()
    input.forEach { current = ImmutableList.builder<T>().addAll(current).addAll(it).build() }
    return current
}
