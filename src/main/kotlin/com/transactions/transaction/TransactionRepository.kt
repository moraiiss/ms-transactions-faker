package com.transactions.transaction

import jakarta.transaction.Transaction
import org.springframework.data.repository.ListCrudRepository

interface TransactionRepository : ListCrudRepository<Transaction, Long>