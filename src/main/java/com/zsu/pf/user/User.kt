package com.zsu.pf.user

typealias UserToken = String
typealias ServerToken = String
typealias LocalDb = String
typealias LayoutParameters = String
typealias UIData = String

class UserService(private val id: Int) {
    fun userToken(password: String): UserToken = TODO()
    fun localDb(dbPassword: String): LocalDb = TODO()
}

class ServerService(private val ip: String, private val port: Int) {
    fun serverToken(serverSecret: String): ServerToken = TODO()
    fun getUser(userToken: UserToken): UserData = TODO()
}

class UserData(
    val name: String, val avatarUrl: String, val description: String,
) {
    fun uiData(parentLayoutParameters: LayoutParameters): UIData = TODO()
}

fun main() {
    val parentLayoutParameters = ""
    val userService = UserService(id = 0)
    val serverService = ServerService(ip = "0.0.0.0", port = 114514)
    val userToken = userService.userToken(password = "undefined")
    val userData = serverService.getUser(userToken)
    val uiData = userData.uiData(parentLayoutParameters)
}

