package com.transactions.transaction

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("transactions")
class TransactionController (
    val service: TransactionService
) {
    @PostMapping
    fun createTransaction(@RequestBody transaction: Transaction): Transaction {
        return service.create(transaction)
    }

    @GetMapping
    fun listTransactions(): MutableList<Transaction> {
        return service.list()
    }
}