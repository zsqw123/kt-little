package com.zsu.immutable

import com.zsu.immutable.my.MyPersistentList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.plus
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() {
    val random = Random(System.currentTimeMillis())
    val payload = List(100000) {
        List(random.nextInt(8, 200)) { random.nextInt() }
    }
    var flattenData: List<Int>
    var ktData: List<Int>
    var myData: List<Int>
    println("flatten cost ${measureTimeMillis { flattenData = flatten(payload) }}ms")
    println("kt immutable cost ${measureTimeMillis { ktData = ktImmutable(payload) }}ms")
    println("myImmutable cost ${measureTimeMillis { myData = myImmutable(payload) }}ms")
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
