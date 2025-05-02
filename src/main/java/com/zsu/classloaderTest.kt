package com.zsu

import com.google.gson.Gson
import com.google.gson.JsonParseException
import java.io.File
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class BarLoadTest

class FooTest {
    val bar: BarLoadTest? = null
}

sealed class Res<T> {
    class S<T>(val v: T) : Res<T>()
    class F(msg: String) : Res<Nothing>()

    @OptIn(ExperimentalContracts::class)
    fun isSuccess(): Boolean {
        contract {
            returns(true) implies (this@Res is S<T>)
        }
        return this is S<T>
    }
}

fun getRes(): Res<String> = TODO()

fun aaa() {
}

fun main() {
    aaa()
}
