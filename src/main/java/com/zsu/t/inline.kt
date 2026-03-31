package com.zsu.t

import kotlin.reflect.typeOf

@Suppress("NOT_YET_SUPPORTED_IN_INLINE")
fun main() {
    var c = 10
    typeOf<String>()
    aa()
}

inline fun aa() {
    val c = 20
//    val s = "SMAP\ninline.kt\nKotlin\n*S Kotlin\n*F\n+ 1 inline.kt\ncom/zsu/t/InlineKt\n*L\n1#1,13:1\n10#1,3:14\n*S KotlinDebug\n*F\n+ 1 inline.kt\ncom/zsu/t/InlineKt\n*L\n6#1:14,3\n*E\n"
    println()
}
