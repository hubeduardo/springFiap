package com.fiap.api.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fiap.api.entities.request.CreateTransactionRequest
import com.fiap.api.security.Encrypt
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate


@Document(collection = "transaction")
data class Transaction(

        @Id
        @JsonProperty("id")
        val id: String? = ObjectId().toHexString(),

        @JsonProperty("user_doc")
        val userDoc: String = "",

        @JsonProperty("date_Transaction")
        val dateTransaction: LocalDate? = null,

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
