package com.fiap.api.entities.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class UpdateUserRequest(

    @field:NotNull
    @field:NotEmpty
    @JsonProperty("id")
    val id: String = "",

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

    @JsonProperty("birthday")
    val birthday: LocalDateTime? = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)

)
