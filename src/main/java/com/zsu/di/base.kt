package com.zsu.di

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

inline val di get() = NothingDI


object NothingDI : ReadOnlyProperty<Any?, Nothing> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Nothing {
        throw IllegalStateException("No DI found")
    }
}

private val s: String by di

annotation class Component
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
annotation class Named(val value: String = "")
annotation class Provides(val target: Array<KClass<*>> = [])