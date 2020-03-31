package com.fiap.api.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fiap.api.entities.request.CreateTransactionRequest
import java.time.LocalDateTime
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "transaction")
data class Transaction(

    @Id
    @JsonProperty("id")
    val id: String? = ObjectId().toHexString(),

    @JsonProperty("user_doc")
    val userDoc: String = "",

    @JsonProperty("date_transaction")
    val dateTransaction: LocalDateTime = LocalDateTime.now(),

    @JsonProperty("description")
    val description: String = "",

    @JsonProperty("amount")
    val amount: Double = 0.0

) {

    fun convertToTransaction(createTransactionRequest: CreateTransactionRequest): Transaction {

        return Transaction(
                userDoc = createTransactionRequest.userDoc,
                dateTransaction = createTransactionRequest.dateTransaction,
                description = createTransactionRequest.description,
                amount = createTransactionRequest.amount
        )
    }
}
