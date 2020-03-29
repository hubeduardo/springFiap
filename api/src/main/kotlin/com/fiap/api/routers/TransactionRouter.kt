package com.fiap.api.routers

object TransactionRouter {
    const val BASE_PATH = "/v1/fiap"
    const val CREATE_TRANSACTION_V1 = "$BASE_PATH/create-transaction"
    const val GENERATE_EXTRATO_V1 = "$BASE_PATH/generate-extrato/{doc}"
}
