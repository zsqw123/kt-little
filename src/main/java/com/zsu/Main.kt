package com.zsu

import kotlin.reflect.KClass

fun main() {
    var a = 1
    var b = 2
    a = b.also { b = a }
    println(a)
    println(b)
    test(a = "a", c = "c")
}

fun test(a: String, b: String = "b", c: String) {

}

annotation class Component
annotation class Provides(val target: Array<KClass<*>> = [])
