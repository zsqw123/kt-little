@file:Suppress("FunctionName")

package com.zsu.di


@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
annotation class Composable

const val SETTING_COMPOSABLE = "setting_composable"

@Named(SETTING_COMPOSABLE)
@Provides
@Composable
fun Opt1() {

}

@Named(SETTING_COMPOSABLE)
@Provides
@Composable
fun Opt2() {

}

object Container {
    val settings: List<@Named(SETTING_COMPOSABLE) @Composable () -> Unit> by di
}

@Composable
fun SettingPage() {
    Container.settings.forEach {
        it()
    }
}

fun main() {
    SettingPage()
}
