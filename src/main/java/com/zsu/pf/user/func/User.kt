package com.zsu.pf.user.func

import com.zsu.pf.user.*

typealias UserTokenService = (password: String) -> UserToken
typealias LocalDbService = (dbPassword: String) -> LocalDb

typealias UserService = (id: Int) -> Pair<UserTokenService, LocalDbService>

typealias ServerTokenService = (serverSecret: String) -> ServerToken
typealias ServerUserService = (userToken: UserToken) -> UserDataAbilities
typealias ServerService = (ip: String, port: Int) -> Pair<ServerTokenService, ServerUserService>

typealias UserUIData = (parentLayoutParameters: LayoutParameters) -> UIData
typealias UserDataAbilities = UserUIData

val userService: UserService = { userId: Int ->
    val tokenService: UserTokenService = { password: String -> TODO() }
    val localDbService: LocalDbService = { dbPassword: String -> TODO() }
    tokenService to localDbService
}

val serverService: ServerService = { ip: String, port: Int ->
    val tokenService: ServerTokenService = { serverSecret: String -> TODO() }
    val userService: ServerUserService = { userToken: String -> TODO() }
    tokenService to userService
}

fun main() {
    val parentLayoutParameters = ""
    val (userTokenService, _) = userService(0)
    val (_, userDataService) = serverService("0.0.0.0", 114514)
    val userToken = userTokenService("undefined")
    val userData = userDataService(userToken)
    val uiData = userData(parentLayoutParameters)
}
