package com.fiap.api.entities.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@JsonIgnoreProperties(ignoreUnknown = true)
data class CreateTransactionRequest(

    @field:NotNull
    @field:NotEmpty
    @JsonProperty("user_doc")
    var userDoc: String = "",

    @JsonProperty("date_transaction")
    var dateTransaction: LocalDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),

    @field:NotNull
    @field:NotEmpty
    @JsonProperty("description")
    var description: String = "",

    @JsonProperty("amount")
    val amount: Double = 0.0

)
