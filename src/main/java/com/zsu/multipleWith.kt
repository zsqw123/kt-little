package com.zsu

class Foo(val foo: Int = 1)
class Bar(val bar: Int = 2)
class Baz(val baz: Int = 3)
class Qux(val qux: Int = 4)

fun main() {
    context(Foo(), Bar(), Baz(), Qux()) {
        contextOf<Foo>().foo
        contextOf<Bar>().bar
        contextOf<Baz>().baz
        contextOf<Qux>().qux
    }
}