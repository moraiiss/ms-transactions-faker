package com.transactions.authorization

import com.transactions.transaction.Transaction
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class AuthorizerService (
    builder: RestClient.Builder
) {
    val restClient: RestClient = builder
        .baseUrl("https://apimocha.com/transactionsfaker/auth")
        .build()

    fun authorize(transaction: Transaction) {
        val response = restClient
            .get()
            .retrieve()
            .toEntity(Authorization::class.java)

        if (response.statusCode.isError || !response.body?.isAuthorized()!!) {
            throw UnauthorizedException("Unauthorized")
        }
    }
}