package com.fiap.api.routers

object UserRouter {
    const val BASE_PATH = "/v1/fiap"
    const val CREATE_USER_V1 = "$BASE_PATH/create-user"
    const val DELETE_USER_V1 = "$BASE_PATH/delete-user"
    const val UPDATE_USER_V1 = "$BASE_PATH/update-user"
    const val GET_USER_V1 = "$BASE_PATH/get-user/{doc}"
}
