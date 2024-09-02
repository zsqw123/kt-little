package com.zsu.asm

class ShowFrameSampleKt {
    private fun test(a: Int, b: Long) {
        val c = a + 10
        val s = ""
        bar(
            a, if (a > b) {
                val d1 = a - b
                if (d1 > 0) {
                    println(c)
                } else {
                    println(s)
                }
                d1
            } else b
        )
    }

    private fun bar(var1: Number, var2: Number) = Unit
}
