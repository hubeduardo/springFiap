package com.fiap.api.entities.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import org.springframework.format.annotation.DateTimeFormat

data class UpdateUserRequest(

    @field:NotNull
    @field:NotEmpty
    @JsonProperty("id")
    val id: String = "",

    @field:NotNull
    @field:NotEmpty
    @JsonProperty("name")
    var name: String = "",

    @JsonProperty("last_name")
    var lastName: String = "",

    @JsonProperty("email")
    var email: String = "",

    @JsonProperty("image_url")
    var imageUrl: String = "",

    @JsonProperty("password")
    val password: String = "",

    @field:NotNull
    @field:NotEmpty
    @get:DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("birthday")
    val birthday: LocalDate? = null

)
