package com.transactions.transaction


import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : ListCrudRepository<Transaction, Long>