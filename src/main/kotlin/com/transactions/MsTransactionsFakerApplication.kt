package com.transactions

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing

@SpringBootApplication
class MsTransactionsFakerApplication

fun main(args: Array<String>) {
    runApplication<MsTransactionsFakerApplication>(*args)
}
