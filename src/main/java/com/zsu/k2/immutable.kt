package com.zsu.k2

data class Event(
    var messages: List<String>,
)

val event = Event(arrayListOf())

fun send(message: String) {
    event.messages += message
    notifyEvent(event.copy())
}

fun notifyEvent(event: Event) {

}
