package com.transactions.authorization

import com.transactions.transaction.Transaction
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class AuthorizerService (
    builder: RestClient.Builder
) {
    val restClient: RestClient = builder
        .baseUrl("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc")
        .build()

    fun authorize(transaction: Transaction) {
        val response = restClient
            .get()
            .retrieve()
            .toEntity(Authorization::class.java)

        if (response.statusCode.isError || !response.body?.isAuthorized()!!) {
            throw RuntimeException("Unauthorized")
        }
    }
}