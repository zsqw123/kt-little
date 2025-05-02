package com.zsu

import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.reflect.KClass

fun main() {
    Job()
    var a = 1
    var b = 2
    a = b.also { b = a }
    println(a)
    println(b)
    test(a = "a", c = "c")
}

fun test(a: String, b: String = "b", c: String) {
    val kC: Collection<String> = listOf()
}
