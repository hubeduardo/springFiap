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
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @PutMapping(UserRouter.UPDATE_USER_V1)
    fun updateUser(
        @Valid @RequestBody updateUserRequest: UpdateUserRequest
    ): Future<User> {

        return userService.checkUpdateUser(updateUserRequest).toFutureResponse()
    }

    @DeleteMapping(UserRouter.DELETE_USER_V1)
    fun removeUser(
        @Valid @RequestBody deleteRequest: DeleteRequest
    ): Future<DeleteResponse> {

        return userService.checkRemoveUser(deleteRequest).toFutureResponse()
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
