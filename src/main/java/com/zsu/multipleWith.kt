@file:Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")

package com.zsu

inline fun <V1, V2, R> with(v1: V1, v2: V2, block: context(V1, V2) () -> R) = block(v1, v2)
inline fun <V1, V2, V3, R> with(v1: V1, v2: V2, v3: V3, block: context(V1, V2, V3) () -> R) = block(v1, v2, v3)
inline fun <V1, V2, V3, V4, R> with(v1: V1, v2: V2, v3: V3, v4: V4, block: context(V1, V2, V3, V4) () -> R) = block(v1, v2, v3, v4)
inline fun <V1, V2, V3, V4, V5, R> with(v1: V1, v2: V2, v3: V3, v4: V4, v5: V5, block: context(V1, V2, V3, V4, V5) () -> R) = block(v1, v2, v3, v4, v5)
inline fun <V1, V2, V3, V4, V5, V6, R> with(v1: V1, v2: V2, v3: V3, v4: V4, v5: V5, v6: V6, block: context(V1, V2, V3, V4, V5, V6) () -> R) = block(v1, v2, v3, v4, v5, v6)
inline fun <V1, V2, V3, V4, V5, V6, V7, R> with(v1: V1, v2: V2, v3: V3, v4: V4, v5: V5, v6: V6, v7: V7, block: context(V1, V2, V3, V4, V5, V6, V7) () -> R) = block(v1, v2, v3, v4, v5, v6, v7)
inline fun <V1, V2, V3, V4, V5, V6, V7, V8, R> with(v1: V1, v2: V2, v3: V3, v4: V4, v5: V5, v6: V6, v7: V7, v8: V8, block: context(V1, V2, V3, V4, V5, V6, V7, V8) () -> R) = block(v1, v2, v3, v4, v5, v6, v7, v8)
inline fun <V1, V2, V3, V4, V5, V6, V7, V8, V9, R> with(v1: V1, v2: V2, v3: V3, v4: V4, v5: V5, v6: V6, v7: V7, v8: V8, v9: V9, block: context(V1, V2, V3, V4, V5, V6, V7, V8, V9) () -> R) = block(v1, v2, v3, v4, v5, v6, v7, v8, v9)

class Foo(val foo: Int = 1)
class Bar(val bar: Int = 2)
class Baz(val baz: Int = 3)
class Qux(val qux: Int = 4)

fun main() {
    with(Foo(), Bar(), Baz(), Qux()) {
        println("$foo $bar $baz $qux")
    }
}