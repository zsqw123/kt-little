package com.zsu.t

fun ccc(arg: String?) = 1

fun p(): Any? = null

fun main() {
    ccc(p() as? String)
}
