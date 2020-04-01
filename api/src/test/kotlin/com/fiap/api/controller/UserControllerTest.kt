package com.fiap.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fiap.api.domain.User
import com.fiap.api.entities.request.CreateUserRequest
import com.fiap.api.entities.request.DeleteRequest
import com.fiap.api.entities.request.UpdateUserRequest
import com.fiap.api.entities.response.DeleteResponse
import com.fiap.api.routers.UserRouter
import com.fiap.api.service.UserService
import io.reactivex.Single
import kotlin.collections.HashMap
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@RunWith(SpringRunner::class)
@WebMvcTest(UserController::class)
class UserControllerTest {

    @MockBean
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Before
    fun initMocks() {
        MockitoAnnotations.initMocks(this)
    }

    private fun getUserRequest(): CreateUserRequest {

        return CreateUserRequest(name = "name",
                lastName = "lastName",
                email = "teste@gmail.com",
                imageUrl = "",
                doc = "111.222.333.444",
                password = "teste"
        )
    }

    private fun getUser(): User {

        return User(
                id = "5e77608ae8fe1146d83ec679",
                name = "name",
                lastName = "last_name",
                doc = "111.222.333",
                password = "teste",
                email = "teste@gmail.com",
                imageUrl = ""
        )
    }

    @Test
    @Throws(Exception::class)
    fun test_create_user_ok() {
        val request = getUserRequest()

        val response = getUser()

        Mockito.`when`(userService.checkCreateUser(request)).thenReturn(Single.just(response))

        this.mvc.perform(MockMvcRequestBuilders.post(UserRouter.CREATE_USER_V1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @Throws(Exception::class)
    fun test_create_user_bad_request_header() {
        val request = getUserRequest()
        val response = getUser()

        Mockito.`when`(userService.checkCreateUser(request)).thenReturn(Single.just(response))

        this.mvc.perform(MockMvcRequestBuilders.post(UserRouter.CREATE_USER_V1)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    @Test
    @Throws(Exception::class)
    fun test_create_event_body() {
        val request = CreateUserRequest()
        val response = getUser()

        Mockito.`when`(userService.checkCreateUser(request)).thenReturn(Single.just(response))

        this.mvc.perform(MockMvcRequestBuilders.post(UserRouter.CREATE_USER_V1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    @Test
    @Throws(Exception::class)
    @WithMockUser(username = "aUser", password = "aPassword", roles = ["ADMIN"])
    fun test_remove_user_ok() {
        val request = DeleteRequest("5e0b84ef2ea4095e7c19d782")
        val requestHeader: HashMap<String, String> = hashMapOf("Content-Type" to "application/json", "id" to "123")
        val applicationUserId = requestHeader.get("id")
        val response = DeleteResponse(id = "aaa", message = "remove_user")

        Mockito.`when`(userService.checkRemoveUser(request)).thenReturn(Single.just(response))

        this.mvc.perform(MockMvcRequestBuilders.delete(UserRouter.DELETE_USER_V1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("id", applicationUserId)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @Throws(Exception::class)
    fun test_remove_event_request_header() {
        val request = DeleteRequest("5e0b84ef2ea4095e7c19d782")
        val requestHeader: HashMap<String, String> = hashMapOf("Content-Type" to "application/json", "id" to "123")
        val applicationUserId = requestHeader.get("id")
        val response = DeleteResponse(id = "aaa", message = "remove_event")

        Mockito.`when`(userService.checkRemoveUser(request)).thenReturn(Single.just(response))

        this.mvc.perform(MockMvcRequestBuilders.delete(UserRouter.DELETE_USER_V1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    @Test
    @Throws(Exception::class)
    fun test_remove_user_request_body() {
        val request = DeleteRequest()
        val requestHeader: HashMap<String, String> = hashMapOf("Content-Type" to "application/json", "id" to "123")
        val applicationUserId = requestHeader.get("id")
        val response = DeleteResponse(id = "aaa", message = "remove_event")

        Mockito.`when`(userService.checkRemoveUser(request)).thenReturn(Single.just(response))

        this.mvc.perform(MockMvcRequestBuilders.delete(UserRouter.DELETE_USER_V1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    @Test
    @Throws(Exception::class)
    @WithMockUser(username = "aUser", password = "aPassword", roles = ["ADMIN"])
    fun test_update_user_ok() {
        val response = getUser()
        val request = UpdateUserRequest(id = "5e77608ae8fe1146d83ec679", name = "teste")

        Mockito.`when`(userService.checkUpdateUser(request)).thenReturn(Single.just(response))
        this.mvc.perform(MockMvcRequestBuilders.put(UserRouter.UPDATE_USER_V1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @Throws(Exception::class)
    fun test_update_user_ok_request_header() {
        val request = UpdateUserRequest(id = "w123")
        val response = getUser()

        Mockito.`when`(userService.checkUpdateUser(request)).thenReturn(Single.just(response))

        this.mvc.perform(MockMvcRequestBuilders.put(UserRouter.UPDATE_USER_V1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    @Test
    @Throws(Exception::class)
    fun test_update_user_ok_request_body() {
        val request = UpdateUserRequest(name = "NewName")
        val requestHeader: HashMap<String, String> = hashMapOf("Content-Type" to "application/json", "id" to "5e77608ae8fe1146d83ec679")
        val applicationUserId = requestHeader.get("id")
        val response = getUser()

        Mockito.`when`(userService.checkUpdateUser(request)).thenReturn(Single.just(response))

        this.mvc.perform(MockMvcRequestBuilders.put(UserRouter.UPDATE_USER_V1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("id", applicationUserId)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }
}
