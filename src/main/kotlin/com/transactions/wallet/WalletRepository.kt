package com.transactions.wallet

import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WalletRepository : ListCrudRepository<Wallet, Long>