package com.transactions.transaction

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/transactions")
@Tag(name = "transactions", description = "Endpoints for managing transactions")
class TransactionController (
    val service: TransactionService
) {
    @PostMapping
    @Operation(summary = "Create transaction", description = "Returns the created transaction")
    fun createTransaction(@RequestBody transaction: Transaction): Transaction {
        return service.create(transaction)
    }

    @GetMapping
    @Operation(summary = "List all transactions", description = "Return a list of all transactions")
    fun listTransactions(): MutableList<Transaction> {
        return service.list()
    }
}