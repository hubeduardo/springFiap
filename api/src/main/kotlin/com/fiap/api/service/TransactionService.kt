package com.fiap.api.service

import com.fiap.api.domain.Transaction
import com.fiap.api.entities.exception.UserException
import com.fiap.api.entities.request.CreateTransactionRequest
import com.fiap.api.repository.TransactionRepository
import com.fiap.api.utils.ErrorCode
import com.fiap.api.utils.Translator
import com.fiap.api.utils.getError
import io.reactivex.Single
import io.reactivex.Single.just
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionService {
    private val logger = LoggerFactory.getLogger(TransactionService::class.java)

    @Autowired
    private lateinit var transactionRepository: TransactionRepository

    fun checkCreateTransaction(createTransactionRequest: CreateTransactionRequest): Single<Transaction> {
        logger.info("Start checkCreateTransaction by request: $createTransactionRequest")

        return saveTransaction(Transaction().convertToTransaction(createTransactionRequest))
    }

    fun saveTransaction(transaction: Transaction): Single<Transaction> {
        logger.info("Start saveTransaction with request: $transaction")

        return save(transaction)
            .doOnSuccess {
                logger.info("Success")
            }.doOnError {
                logger.error("Error with error: ${it.getError()}")
            }.onErrorResumeNext {
                Single.error(UserException("400", Translator.getMessage(ErrorCode.TRANSACTION_TRY_AGAIN_LATER)))
            }
    }

    private fun save(transaction: Transaction) = just(transactionRepository.save(transaction))
}
