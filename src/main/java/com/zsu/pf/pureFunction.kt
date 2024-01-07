package com.zsu.pf

fun main() {
    weight(2)(3)(4)
}

tailrec fun sum(array: IntArray, current: Int = 0, index: Int = 0): Int {
    if (index < 0 || index >= array.size) return current
    return sum(array, current + array[index], index + 1)
}

fun weight(t: Int, kg: Int, g: Int): Int {
    return t * 1000_000 + kg * 1000 + g
}

val weight = { t: Int ->
    { kg: Int ->
        { g: Int ->
            t * 1000_000 + kg * 1000 + g
        }
    }
}


