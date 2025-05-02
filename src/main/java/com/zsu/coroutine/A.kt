package com.zsu.coroutine

import kotlin.math.roundToInt

private val km = LengthUnit(1000)
private val h = TimeUnit(3600)
private val m = LengthUnit(1)
private val s = TimeUnit(1)

private class LengthUnit(val scale: Int) {
    operator fun div(timeUnit: TimeUnit): SpeedUnit = SpeedUnit(
        scale.toFloat() / timeUnit.scale
    )
}

private class TimeUnit(val scale: Int)
private class SpeedUnit(val scale: Float)

private class Speed(val v: Int, val speedUnit: SpeedUnit) {
    override fun toString(): String {
        return "${v * speedUnit.scale} m/s"
    }
}

private operator fun Int.invoke(unit: SpeedUnit) = Speed(this, unit)

fun main() {
    val a = 100(km / h)
    println(a)
    val b = 5(km / s)
    println(b)
}