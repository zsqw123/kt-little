package com.zsu

fun main() {

}

tailrec fun sum(array: IntArray, current: Int = 0, index: Int = 0): Int {
    if (index < 0 || index >= array.size) return current
    return sum(array, current + array[index], index + 1)
}


