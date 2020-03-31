package com.fiap.api.controller

import com.fiap.api.domain.Transaction
import com.fiap.api.domain.User
import com.fiap.api.entities.request.CreateTransactionRequest
import com.fiap.api.repository.TransactionRepository
import com.fiap.api.repository.UserRepository
import com.fiap.api.routers.TransactionRouter
import com.fiap.api.service.TransactionService
import com.fiap.api.service.UserService
import com.fiap.api.utils.PDFService
import com.fiap.api.utils.toFutureResponse
import java.io.ByteArrayInputStream
import java.util.concurrent.Future
import javax.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TransactionController {

    @Autowired
    private lateinit var transactionService: TransactionService

    @Autowired
    private lateinit var transactionRepository: TransactionRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var userService: UserService

    @PostMapping(TransactionRouter.CREATE_TRANSACTION_V1)
    fun createTransaction(
        @Valid @RequestBody createTransactionRequest: CreateTransactionRequest
    ): Future<Transaction> {

        return transactionService.checkCreateTransaction(createTransactionRequest).toFutureResponse()
    }

    @GetMapping(TransactionRouter.GENERATE_EXTRATO_V1)
    fun generateExtrato(
        @PathVariable("doc") doc: String
    ): ResponseEntity<InputStreamResource> {

        var user: User = userRepository.findByDoc(doc).first()

        var transactions: MutableList<Transaction> = transactionRepository.findByuserDoc(doc)

        val bis: ByteArrayInputStream = PDFService.report(user, transactions)

        val headers: HttpHeaders = HttpHeaders()
        headers.add("Content-Disposition", "inline; filename=report.pdf")

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(InputStreamResource(bis))
    }
}
