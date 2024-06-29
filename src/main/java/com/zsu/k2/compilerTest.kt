package com.zsu.k2

fun fastConvert(input: Int): String {
    val config: MagicConfig<in Int, *, String>? = null
    return Converter(config).convert(input)
}

interface SimpleConfig<I, O>

class Converter<I : O, O>(configuration: SimpleConfig<I, O>?) {
    fun convert(input: I): O = input
}

interface MagicConfig<I : X, X : O, O> : SimpleConfig<I, O>

fun main() {
    fastConvert(1)

    val result = runCatching { 1 }
    result.mapCatching {  }
}

fun Result<String>.parse() : Result<Int> {
    return mapCatching { Integer.parseInt(it) }
}



sealed class Container<T>(val value: T)
class IntContainer : Container<Int>(42)
class StringContainer : Container<String>("Kotlin")

fun <A> unbox(container: Container<A>): A = container.value // 0K!

@Suppress("UNCHECKED_CAST")
fun <A> unboxAndProcess(container: Container<A>): A = when (container) {
    is IntContainer -> 42 as A
    is StringContainer -> "Kotlin" as A
}
