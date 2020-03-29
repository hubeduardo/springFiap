package com.fiap.api.controller

import com.fiap.api.domain.User
import com.fiap.api.entities.request.CreateUserRequest
import com.fiap.api.entities.request.DeleteRequest
import com.fiap.api.entities.request.UpdateUserRequest
import com.fiap.api.entities.response.DeleteResponse
import com.fiap.api.routers.UserRouter
import com.fiap.api.service.UserService
import com.fiap.api.utils.toFutureResponse
import java.util.concurrent.Future
import javax.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import springfox.documentation.swagger2.annotations.EnableSwagger2

@RestController
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @PutMapping(UserRouter.UPDATE_USER_V1)
    fun updateUser(
            @Valid @RequestBody updateUserRequest: UpdateUserRequest,
            @RequestHeader(value = "id") applicationUserId: String
    ): Future<User> {

        return userService.checkUpdateUser(updateUserRequest, applicationUserId).toFutureResponse()
    }

    @DeleteMapping(UserRouter.DELETE_USER_V1)
    fun removeUser(
        @Valid @RequestBody deleteRequest: DeleteRequest,
        @RequestHeader(value = "id") applicationUserId: String
    ): Future<DeleteResponse> {

        return userService.checkRemoveUser(deleteRequest, applicationUserId).toFutureResponse()
    }

    @PostMapping(UserRouter.CREATE_USER_V1)
    fun createUser(
            @Valid @RequestBody createUserRequest: CreateUserRequest
    ): Future<User> {

        return userService.checkCreateUser(createUserRequest).toFutureResponse()
    }

    @GetMapping(UserRouter.GET_USER_V1)
    fun getUserDoc(
        @PathVariable("doc") doc: String
    ): Future<MutableList<User>> {

        return userService.findByDoc(doc).toFutureResponse()
    }
}
