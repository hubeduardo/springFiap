package com.fiap.api.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fiap.api.entities.request.CreateUserRequest
import com.fiap.api.entities.request.UpdateUserRequest
import com.fiap.api.security.Encrypt
import java.time.LocalDate
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user")
data class User(

    @Id
    @JsonProperty("id")
    val id: String? = ObjectId().toHexString(),

    @JsonProperty("name")
    val name: String = "",

    @JsonProperty("last_name")
    val lastName: String = "",

    @JsonProperty("email")
    val email: String = "",

    @JsonProperty("doc")
    val doc: String = "",

    @JsonProperty("image_url")
    val imageUrl: String = "",

    @JsonProperty("version")
    val version: Long = 1,

    @JsonProperty("password")
    val password: String = "",

    @JsonProperty("birthday")
    val birthday: LocalDate = LocalDate.now()

) {
    fun mergeDataUser(newUser: UpdateUserRequest, oldUser: User): User {

        return User(
            id = oldUser.id,
            name = if (newUser.name == "") oldUser.name else newUser.name,
            lastName = if (newUser.lastName == "") oldUser.lastName else newUser.lastName,
            email = if (newUser.email == "") oldUser.email else newUser.email,
            doc = if (newUser.doc == "") oldUser.doc else newUser.doc,
            imageUrl = if (newUser.imageUrl == "") oldUser.imageUrl else newUser.imageUrl,
            password = if (newUser.password == "") oldUser.password else newUser.password,
            version = oldUser.version + 1,
            birthday = newUser.birthday ?: oldUser.birthday

        )
    }

    fun convertToUser(createUserRequest: CreateUserRequest): User {

        return User(
            name = createUserRequest.name,
            lastName = createUserRequest.lastName,
            email = createUserRequest.email,
            doc = createUserRequest.doc,
            imageUrl = createUserRequest.imageUrl,
            password = Encrypt().generateEncryptPassword(createUserRequest.password),
            birthday = createUserRequest.birthday
        )
    }
}
