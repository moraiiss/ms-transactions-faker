package com.transactions

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MsTransactionsFakerApplication

fun main(args: Array<String>) {
    runApplication<MsTransactionsFakerApplication>(*args)
}
