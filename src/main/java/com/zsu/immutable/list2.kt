package com.zsu.immutable

import com.zsu.immutable.my.MyPersistentList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.plus
import kotlinx.collections.immutable.toPersistentList
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() {
    val random = Random(System.currentTimeMillis())
    val payload = List(20000) {
        List(1200) { random.nextInt() }
    }
    val ktPayload = payload.map { it.toPersistentList() }
    val myPayload = payload.map { MyPersistentList.copyOf(it) }
    var flattenData: List<Int>
    var myData: List<Int>
    println("flatten cost ${measureTimeMillis { flattenData = flatten(payload) }}ms")
    println("kt immutable cost ${measureTimeMillis { ktImmutable(ktPayload) }}ms")
    println("myImmutable cost ${measureTimeMillis { myData = myImmutable(myPayload) }}ms")
    println("equals: ${flattenData.toList() == myData.toList()}")
}

private fun <T> flatten(payload: List<List<T>>): List<T> = payload.flatten()

private fun <T> ktImmutable(input: List<List<T>>): List<T> {
    var current = persistentListOf<T>()
    input.forEach { current += it }
    return current
}

private fun <T> myImmutable(input: List<List<T>>): List<T> {
    var current: PersistentList<T> = MyPersistentList.of()
    input.forEach { current += it }
    return current
}
